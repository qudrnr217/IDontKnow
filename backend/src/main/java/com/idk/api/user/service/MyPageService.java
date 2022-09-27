package com.idk.api.user.service;

import com.idk.api.common.exception.PermissionException;
import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.idk.api.districtcode.domain.repository.DistrictCodeRepository;
import com.idk.api.districtcode.exception.DistrictCodeNotFoundException;
import com.idk.api.user.domain.Role;
import com.idk.api.user.dto.MyPageRequest;
import com.idk.api.user.dto.MyPageResponse;
import com.idk.api.user.domain.entity.User;
import com.idk.api.user.domain.repository.UserRepository;
import com.idk.api.user.dto.UserResponse;
import com.idk.api.user.exception.InvalidPasswordException;
import com.idk.api.user.exception.UserNotFoundException;
import com.idk.api.vote.domain.entity.Ballot;
import com.idk.api.vote.domain.entity.Vote;
import com.idk.api.vote.domain.repository.BallotRepository;
import com.idk.api.vote.domain.repository.VoteRepository;
import com.idk.api.vote.dto.VoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {

    private final UserRepository userRepository;
    private final DistrictCodeRepository districtCodeRepository;
    private final VoteRepository voteRepository;
    private final BallotRepository ballotRepository;
    private final PasswordEncoder passwordEncoder;

    public MyPageResponse.UserInfo getUserInfo(Long userId){
        User findUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return MyPageResponse.UserInfo.build(findUser);
    }

    @Transactional
    public MyPageResponse.UserInfo updateUserInfo(Long userId, User user, int districtId, String gender, int age){
        User findUser = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);
        DistrictCode districtCode = null;
        if(districtId != 0) districtCode = districtCodeRepository.findById(districtId).orElseThrow(DistrictCodeNotFoundException::new);
        if(!userId.equals(user.getId()))    throw new PermissionException();
        findUser.updateUserInfo(districtCode, gender, age);
        return MyPageResponse.UserInfo.build(findUser);

    }

    @Transactional
    public UserResponse.OnlyId deleteUserInfo(Long userId, User user){
        User findUser = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);
        if(!userId.equals(user.getId()))    throw new PermissionException();
        findUser.deleteInfo();
        return UserResponse.OnlyId.build(findUser);
    }

    @Transactional
    public UserResponse.OnlyId updateUserPassword(Long userId, User user, MyPageRequest.UserPassword request){
        User findUser = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);
        if(checkPermission(user, userId))    throw new PermissionException();
        if(!passwordEncoder.matches(request.getCurPassword(), user.getPassword())) throw new InvalidPasswordException();
        findUser.updatePassword(passwordEncoder.encode(request.getNewPassword()));
        return UserResponse.OnlyId.build(findUser);
    }

    public Page<VoteResponse.GetOne> getVoteList(Long userId, Long lastVoteId, User user, boolean status) {
        if(checkPermission(user, userId))    throw new PermissionException();
        Pageable of = PageRequest.of(0, 10);
        Page<Vote> votes = voteRepository.findAllByUserAndStatusOrderByDesc(lastVoteId, user, status, of);
        return votes.map(VoteResponse.GetOne::build);
    }

    public Page<VoteResponse.GetOne> getBallotList(Long userId, Long lastVoteId, User user, boolean status) {
        if(checkPermission(user, userId))    throw new PermissionException();
        Pageable of = PageRequest.of(0, 10);
        Page<Vote> votes = voteRepository.findAllByBallotUserAndStatusOrderByDesc(lastVoteId, user, status, of);
        return votes.map(VoteResponse.GetOne::build);
    }

    private boolean checkPermission(User currentUser, long userId){
        return !currentUser.getRole().equals(Role.ADMIN)  && !Objects.equals(currentUser.getId(), userId);
    }

    public MyPageResponse.Rate getRate(Long userId, User user){
        User findUser = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);
        if(checkPermission(user, userId))   throw new PermissionException();
        List<Ballot> ballotList = ballotRepository.findBallotsByUser(findUser);
        List<Vote> voteList = new ArrayList<>();
        for (Ballot ballot : ballotList) {
            voteList.add(voteRepository.findById(ballot.getVote().getId()).get());
        }
        int ballotCount = 0, correctCount = 0;
        for (Vote vote : voteList) {
            if(vote.isStatus() && vote.getDeletedAt() == null)  ballotCount++;
        }
        for(int i = 0; i < ballotList.size(); i++){
            if(ballotList.get(i).getChoice().equals(voteList.get(i).getResult()))   correctCount++;
        }
        return MyPageResponse.Rate.build(userId, ballotCount, correctCount);
    }
}

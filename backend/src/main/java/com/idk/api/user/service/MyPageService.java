package com.idk.api.user.service;

import com.idk.api.common.exception.PermissionException;
import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.idk.api.districtcode.domain.repository.DistrictCodeRepository;
import com.idk.api.districtcode.exception.DistrictCodeNotFoundException;
import com.idk.api.user.dto.MyPageRequest;
import com.idk.api.user.dto.MyPageResponse;
import com.idk.api.user.domain.entity.User;
import com.idk.api.user.domain.repository.UserRepository;
import com.idk.api.user.dto.UserResponse;
import com.idk.api.user.exception.InvalidPasswordException;
import com.idk.api.user.exception.UserNotFoundException;
import com.idk.api.vote.domain.entity.Vote;
import com.idk.api.vote.domain.repository.VoteRepository;
import com.idk.api.vote.dto.VoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {

    private final UserRepository userRepository;
    private final DistrictCodeRepository districtCodeRepository;
    private final VoteRepository voteRepository;
    private final PasswordEncoder passwordEncoder;

    public MyPageResponse.UserInfo getUserInfo(Long userId){
        User findUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return MyPageResponse.UserInfo.build(findUser);
    }

    @Transactional
    public MyPageResponse.UserInfo updateUserInfo(Long userId, User user, MyPageRequest.UserInfo request){
        DistrictCode districtCode = null;
        if(request.getDistrictId() != 0) districtCode = districtCodeRepository.findById(request.getDistrictId()).orElseThrow(DistrictCodeNotFoundException::new);
        if(!userId.equals(user.getId()))    throw new PermissionException();
        user.updateUserInfo(districtCode, request.getGender(), request.getAge());
        userRepository.save(user);
        return MyPageResponse.UserInfo.build(user);

    }

    @Transactional
    public UserResponse.OnlyId deleteUserInfo(Long userId, User user){
        if(!userId.equals(user.getId()))    throw new PermissionException();
        user.setDeletedAt(LocalDateTime.now());
        user.deleteName();
        userRepository.save(user);
        return UserResponse.OnlyId.build(user);
    }

    @Transactional
    public UserResponse.OnlyId updateUserPassword(Long userId, User user, MyPageRequest.UserPassword request){
        if(!userId.equals(user.getId()))    throw new PermissionException();
        if(!passwordEncoder.matches(request.getCurPassword(), user.getPassword())) throw new InvalidPasswordException();
        user.updatePassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return UserResponse.OnlyId.build(user);
    }

    public Page<VoteResponse.GetOne> getVoteList(Long userId, Long lastVoteId, User user, boolean status) {
        if(!userId.equals(user.getId()))    throw new PermissionException();
        Pageable of = PageRequest.of(0, 10);
        Page<Vote> votes = voteRepository.findAllByUserAndStatusOrderByDesc(lastVoteId, user, status, of);
        return votes.map(VoteResponse.GetOne::build);
    }

    public Page<VoteResponse.GetOne> getBallotList(Long userId, Long lastVoteId, User user, boolean status) {
        if(!userId.equals(user.getId()))    throw new PermissionException();
        Pageable of = PageRequest.of(0, 10);
        Page<Vote> votes = voteRepository.findAllByBallotUserAndStatusOrderByDesc(lastVoteId, user, status, of);
        return votes.map(VoteResponse.GetOne::build);
    }
}

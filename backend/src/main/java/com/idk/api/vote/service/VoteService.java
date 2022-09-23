package com.idk.api.vote.service;

import com.idk.api.common.exception.PermissionException;
import com.idk.api.user.domain.Role;
import com.idk.api.user.domain.entity.User;
import com.idk.api.user.security.userdetails.CustomUserDetails;
import com.idk.api.vote.domain.entity.Ballot;
import com.idk.api.vote.domain.entity.Vote;
import com.idk.api.vote.domain.repository.VoteRepository;
import com.idk.api.vote.dto.VoteRequest;
import com.idk.api.vote.dto.VoteResponse;
import com.idk.api.vote.exception.VoteCompletedException;
import com.idk.api.vote.exception.VoteDeletedException;
import com.idk.api.vote.exception.VoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VoteService {
    public final VoteRepository voteRepository;

    @Transactional
    public VoteResponse.OnlyId create(VoteRequest.Create request, User user) {
        Vote vote = Vote.create(request, user);
        Vote savedVote = voteRepository.save(vote);
        return VoteResponse.OnlyId.build(savedVote);
    }

    @Transactional
    public VoteResponse.Info getOne(Long voteId, CustomUserDetails customUserDetails) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(VoteNotFoundException::new);
        if(vote.getDeletedAt() != null) {
            throw new VoteDeletedException();
        }
        vote.hit();
        Ballot ballot = vote.getVoted(customUserDetails);
        return VoteResponse.Info.build(vote, ballot);
    }
    @Transactional
    public VoteResponse.OnlyId changeStatus(Long voteId, VoteRequest.ChangeStatus request, User user) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(VoteNotFoundException::new);
        if(!checkPermission(user, vote.getUser())) {
            throw new PermissionException();
        }else if(!vote.isStatus()) {
            vote.changeStatus(request);
        }else if(vote.getDeletedAt() == null) {
            throw new VoteDeletedException();
        }else {
            throw new VoteCompletedException();
        }
        return VoteResponse.OnlyId.build(vote);
    }

    @Transactional
    public VoteResponse.OnlyId delete(Long voteId, User user) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(VoteNotFoundException::new);
        if(!checkPermission(user, vote.getUser())) {
            throw new PermissionException();
        }
        if(vote.getDeletedAt() != null){
            throw new VoteDeletedException();
        }
        vote.delete(user.getRole().equals(Role.ADMIN));
        return VoteResponse.OnlyId.build(vote);
    }

    private boolean checkPermission(User currentUser, User author) {
        return currentUser.getRole().equals(Role.ADMIN) || currentUser.getId() == author.getId();
    }

}

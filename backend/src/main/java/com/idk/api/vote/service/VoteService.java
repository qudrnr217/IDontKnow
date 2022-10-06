package com.idk.api.vote.service;

import com.idk.api.comment.domain.entity.Comment;
import com.idk.api.comment.domain.repository.CommentRepository;
import com.idk.api.common.category.Category;
import com.idk.api.common.exception.PermissionException;
import com.idk.api.user.domain.Role;
import com.idk.api.user.domain.entity.User;
import com.idk.api.user.security.userdetails.CustomUserDetails;
import com.idk.api.vote.domain.entity.Ballot;
import com.idk.api.vote.domain.entity.Vote;
import com.idk.api.vote.domain.repository.BallotRepository;
import com.idk.api.vote.domain.repository.VoteRepository;
import com.idk.api.vote.dto.VoteRequest;
import com.idk.api.vote.dto.VoteResponse;
import com.idk.api.vote.exception.VoteCompletedException;
import com.idk.api.vote.exception.VoteDeletedException;
import com.idk.api.vote.exception.VoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VoteService {
    public final VoteRepository voteRepository;
    public final BallotRepository ballotRepository;
    private final CommentRepository commentRepository;
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
        int countA = ballotRepository.countAllByVoteAndChoice(vote, "A");
        int countB = ballotRepository.countAllByVoteAndChoice(vote, "B");
        Ballot ballot = vote.getVoted(customUserDetails);
        List<Comment> comments = commentRepository.findAllByVoteOrderByAsc(vote);
        return VoteResponse.Info.build(vote, ballot, comments, countA, countB);
    }
    @Transactional
    public VoteResponse.OnlyId changeStatus(Long voteId, VoteRequest.ChangeStatus request, User user) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(VoteNotFoundException::new);
        int countA = ballotRepository.countAllByVoteAndChoice(vote, "A");
        int countB = ballotRepository.countAllByVoteAndChoice(vote, "B");
        if(checkPermission(user, vote.getUser())) {
            throw new PermissionException();
        }else if(!vote.isStatus()) {
            vote.changeStatus(request, countA, countB);
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
        if(checkPermission(user, vote.getUser())) {
            throw new PermissionException();
        }
        if(vote.getDeletedAt() != null){
            throw new VoteDeletedException();
        }
        vote.delete(user.getRole().equals(Role.ADMIN));
        return VoteResponse.OnlyId.build(vote);
    }

    public Page<VoteResponse.GetOne> getList(Long lastVoteId, String category, boolean status) {
        Pageable of = PageRequest.of(0, 10);
        Page<Vote> votes = voteRepository.findAllByCategoryAndStatusOrderByDesc(lastVoteId, Category.fromString(category), status, of);
        return votes.map(VoteResponse.GetOne::build);
    }

    public List<VoteResponse.GetOne> getTop3(String category) {
        List<Vote> votes = voteRepository.findHitCountTop3ByCategory(Category.fromString(category));
        return votes.stream().map(VoteResponse.GetOne::build).collect(Collectors.toList());
    }

    private boolean checkPermission(User currentUser, User author) {
        return !currentUser.getRole().equals(Role.ADMIN) && !Objects.equals(currentUser.getId(), author.getId());
    }

}

package com.idk.api.vote.service;

import com.idk.api.common.exception.PermissionException;
import com.idk.api.user.domain.entity.User;
import com.idk.api.vote.domain.entity.Ballot;
import com.idk.api.vote.domain.entity.Vote;
import com.idk.api.vote.domain.repository.BallotRepository;
import com.idk.api.vote.domain.repository.VoteRepository;
import com.idk.api.vote.dto.BallotRequest;
import com.idk.api.vote.dto.BallotResponse;
import com.idk.api.vote.exception.BallotDuplicatedException;
import com.idk.api.vote.exception.BallotNotFoundException;
import com.idk.api.vote.exception.VoteCompletedException;
import com.idk.api.vote.exception.VoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BallotService {
    private final BallotRepository ballotRepository;
    private final VoteRepository voteRepository;

    @Transactional
    public BallotResponse.OnlyId create(BallotRequest.Create request, User user) {
        Vote vote = voteRepository.findById(request.getVoteId()).orElseThrow(VoteNotFoundException::new);
        if(ballotRepository.existsByUserAndVote(user, vote)){
            throw new BallotDuplicatedException();
        }
        Ballot ballot = Ballot.create(request, vote, user);
        vote.changeBallotCount(false, request.getChoice());
        Ballot savedBallot = ballotRepository.save(ballot);
        return BallotResponse.OnlyId.build(savedBallot);
    }

    @Transactional
    public BallotResponse.OnlyId delete(Long ballotId, User user) {
        Ballot ballot = ballotRepository.findById(ballotId).orElseThrow(BallotNotFoundException::new);
        if(user.getId() == ballot.getUser().getId()){
            ballotRepository.deleteById(ballot.getId());
        }else throw new PermissionException();
        Vote vote = voteRepository.findById(ballot.getVote().getId()).orElseThrow(VoteNotFoundException::new);
        if(vote.isStatus()) {
            throw new VoteCompletedException();
        }
        vote.changeBallotCount(true, ballot.getChoice());
        return BallotResponse.OnlyId.build(ballot);
    }
}

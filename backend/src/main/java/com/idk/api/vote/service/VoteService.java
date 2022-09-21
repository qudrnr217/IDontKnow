package com.idk.api.vote.service;

import com.idk.api.user.domain.entity.User;
import com.idk.api.vote.domain.entity.Vote;
import com.idk.api.vote.domain.repository.VoteRepository;
import com.idk.api.vote.dto.VoteRequest;
import com.idk.api.vote.dto.VoteResponse;
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
}

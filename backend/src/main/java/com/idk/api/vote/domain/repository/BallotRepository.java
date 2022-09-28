package com.idk.api.vote.domain.repository;

import com.idk.api.user.domain.entity.User;
import com.idk.api.vote.domain.entity.Ballot;
import com.idk.api.vote.domain.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BallotRepository extends JpaRepository<Ballot, Long>, BallotRepositoryCustom {
    boolean existsByUserAndVote(User user, Vote vote);

    List<Ballot> findBallotsByUser(User user);

}

package com.idk.api.vote.domain.repository;

import com.idk.api.vote.domain.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long>, VoteRepositoryCustom {

}

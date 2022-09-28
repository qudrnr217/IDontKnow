package com.idk.api.vote.domain.repository;

import com.idk.api.common.category.Category;
import com.idk.api.user.domain.entity.User;
import com.idk.api.vote.domain.entity.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VoteRepositoryCustom {
    // 기본 투표 목록 조회 : Category별 구분, 진행/종료 상태 구분, DeletedAt 으로 삭제여부 구분
    Page<Vote> findAllByCategoryAndStatusOrderByDesc(Long lastVoteId, Category category, boolean status, Pageable of);

    // 인기 투표 목록 조회 : Category별 구분, 진행중인 투표, 최신순 조회수 높은 것 3개, DeletedAt으로 삭제여부 구분
    List<Vote> findHitCountTop3ByCategory(Category category);

    // 내가 작성한 투표 목록 조회 : 작성자 구분, 진행/종료 상태 구분, DeletedAt 으로 삭제여부 구분
    Page<Vote> findAllByUserAndStatusOrderByDesc(Long lastVoteId, User user, boolean status, Pageable of);

    // 내가 참여한 투표 목록 조회 : 진행/종료 상태 구분, DeletedAt 으로 삭제여부 구분
    Page<Vote> findAllByBallotUserAndStatusOrderByDesc(Long lastVoteId, User user, boolean status, Pageable of);

    List<Vote> countVoteByBallotUser(User user);

    List<Vote> countVoteByBallotUserAndResult(User user);
}

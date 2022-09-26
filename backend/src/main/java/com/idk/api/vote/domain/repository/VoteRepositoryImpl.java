package com.idk.api.vote.domain.repository;

import com.idk.api.common.category.Category;
import com.idk.api.user.domain.entity.User;
import com.idk.api.vote.domain.entity.Vote;
import com.idk.api.vote.dto.BallotDto;
import com.idk.api.vote.dto.QBallotDto_CountByDistrictCode;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

import static com.idk.api.user.domain.entity.QUser.user;
import static com.idk.api.vote.domain.entity.QBallot.ballot;
import static com.idk.api.vote.domain.entity.QVote.vote;
@RequiredArgsConstructor
public class VoteRepositoryImpl implements VoteRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<Vote> findAllByCategoryAndStatusOrderByDesc(Long lastVoteId, Category category, boolean status, Pageable pageable) {
        if(lastVoteId == 0){
            lastVoteId = Long.MAX_VALUE;
        }
        JPAQuery<Vote> query = jpaQueryFactory.selectFrom(vote).distinct()
                                            .where(categoryEq(category),
                                                    statusEq(status),
                                                    vote.id.lt(lastVoteId),
                                                    vote.deletedAt.isNull())
                                            .limit(pageable.getPageSize())
                                            .orderBy(vote.id.desc());
        QueryResults<Vote> results = query.fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public List<Vote> findHitCountTop3ByCategory(Category category) {
        return jpaQueryFactory.selectFrom(vote)
                .where(categoryEq(category),
                        statusEq(false),
                        vote.deletedAt.isNull())
                .limit(3)
                .orderBy(vote.hitCount.desc())
                .orderBy(vote.id.desc())
                .fetch();
    }

    @Override
    public Page<Vote> findAllByUserAndStatusOrderByDesc(Long lastVoteId, User user, boolean status, Pageable pageable) {
        if(lastVoteId == 0){
            lastVoteId = Long.MAX_VALUE;
        }
        JPAQuery<Vote> query = jpaQueryFactory.selectFrom(vote)
                .where(userIdEq(user.getId()),
                        statusEq(status),
                        vote.id.lt(lastVoteId),
                        vote.deletedAt.isNull())
                .limit(pageable.getPageSize())
                .orderBy(vote.id.desc());
        QueryResults<Vote> results = query.fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public Page<Vote> findAllByBallotUserAndStatusOrderByDesc(Long lastVoteId, User user, boolean status, Pageable pageable) {
        if(lastVoteId == 0){
            lastVoteId = Long.MAX_VALUE;
        }
        JPAQuery<Vote> query = jpaQueryFactory.select(vote)
                .from(vote)
                .where(vote.id.in(
                                JPAExpressions
                                        .select(ballot.vote.id)
                                        .from(ballot)
                                        .where(ballot.user.id.eq(user.getId()))
                        ),
                        statusEq(status),
                        vote.id.lt(lastVoteId),
                        vote.deletedAt.isNull())
                .limit(pageable.getPageSize())
                .orderBy(vote.id.desc());
        QueryResults<Vote> results = query.fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    private BooleanExpression categoryEq(Category category) {
        if(Objects.nonNull(category)){
            return vote.category.eq(category);
        }
        return null;
    }

    private BooleanExpression statusEq(boolean status) {
        return vote.status.eq(status);
    }

    private BooleanExpression userIdEq(Long userId) {
        if(Objects.nonNull(userId)){
            return vote.user.id.eq(userId);
        }
        return null;
    }
}

package com.idk.api.vote.domain.repository;

import com.idk.api.vote.dto.BallotDto;
import com.idk.api.vote.dto.QBallotDto_CountByAge;
import com.idk.api.vote.dto.QBallotDto_CountByDistrictCode;
import com.idk.api.vote.dto.QBallotDto_CountByGender;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.idk.api.user.domain.entity.QUser.user;
import static com.idk.api.vote.domain.entity.QBallot.ballot;
import static com.idk.api.vote.domain.entity.QVote.vote;

@RequiredArgsConstructor
public class BallotRepositoryImpl implements BallotRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BallotDto.CountByDistrictCode> countBallotByVoteAndDistrictCodeAndChoice(Long voteId) {
        return jpaQueryFactory.select(new QBallotDto_CountByDistrictCode(ballot.user.districtCode, ballot.choice, ballot.count()))
                .from(ballot)
                .join(ballot.user, user)
                .join(ballot.vote, vote)
                .where(ballot.vote.id.eq(voteId))
                .groupBy(ballot.user.districtCode, ballot.choice)
                .fetch();
    }

    @Override
    public List<BallotDto.CountByGender> countBallotByVoteAndGenderAndChoice(Long voteId) {
        return jpaQueryFactory.select(new QBallotDto_CountByGender(ballot.user.gender, ballot.choice, ballot.count()))
                .from(ballot)
                .join(ballot.user, user)
                .join(ballot.vote, vote)
                .where(ballot.vote.id.eq(voteId))
                .groupBy(ballot.user.gender, ballot.choice)
                .fetch();
    }

    @Override
    public List<BallotDto.CountByAge> countBallotByVoteAndAgeAndChoice(Long voteId) {
        return jpaQueryFactory.select(new QBallotDto_CountByAge(ballot.user.age, ballot.choice, ballot.count()))
                .from(ballot)
                .join(ballot.user, user)
                .join(ballot.vote, vote)
                .where(ballot.vote.id.eq(voteId))
                .groupBy(ballot.user.age, ballot.choice)
                .fetch();
    }


}

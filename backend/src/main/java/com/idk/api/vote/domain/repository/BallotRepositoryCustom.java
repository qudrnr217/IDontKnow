package com.idk.api.vote.domain.repository;

import com.idk.api.vote.dto.BallotDto;

import java.util.List;

public interface BallotRepositoryCustom {
    // 거주지에 따른 AB 투표 개수 조회
    List<BallotDto.CountByDistrictCode> countBallotByVoteAndDistrictCodeAndChoice(Long voteId);

    // 성별에 따른 AB 투표 개수 조회
    List<BallotDto.CountByGender> countBallotByVoteAndGenderAndChoice(Long voteId);

    // 연령대에 따른 AB 투표 개수 조회
    List<BallotDto.CountByAge> countBallotByVoteAndAgeAndChoice(Long voteId);


}

package com.idk.api.vote.dto;

import com.idk.api.vote.domain.entity.Ballot;

import lombok.*;

public class BallotResponse {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class OnlyId {
        private Long id;

        public static BallotResponse.OnlyId build(Ballot ballot) {
            return BallotResponse.OnlyId.builder()
                    .id(ballot.getId())
                    .build();
        }

    }
}

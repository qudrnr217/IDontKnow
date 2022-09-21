package com.idk.api.vote.dto;

import com.idk.api.vote.domain.entity.Vote;
import lombok.*;

public class VoteResponse {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class OnlyId {
        private Long id;

        public static VoteResponse.OnlyId build(Vote vote) {
            return VoteResponse.OnlyId.builder()
                    .id(vote.getId())
                    .build();
        }

    }
}

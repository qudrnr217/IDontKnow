package com.idk.api.vote.dto;

import lombok.*;

public class BallotRequest {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create {
        private Long voteId;
        private String choice;
    }
}

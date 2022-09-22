package com.idk.api.vote.dto;

import com.idk.api.common.category.Category;
import com.idk.api.common.category.SubCategory;
import lombok.*;

public class VoteRequest {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create {
        private String title;
        private String category;
        private String subCategory;
        private String optionA;
        private String optionB;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ChangeStatus {
        private boolean status;
    }

}

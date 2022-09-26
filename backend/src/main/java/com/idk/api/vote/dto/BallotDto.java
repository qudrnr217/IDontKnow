package com.idk.api.vote.dto;

import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

public class BallotDto {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CountByDistrictCode {
        private int districtId;
        private String districtName;
        private String choice;
        private Long count;

        @Builder
        @QueryProjection
        public CountByDistrictCode(DistrictCode districtCode, String choice, Long count) {
            this.districtId = districtCode.getId();
            this.districtName = districtCode.getName();
            this.choice = choice;
            this.count = count;
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CountByGender {
        private String gender;
        private String choice;
        private Long count;

        @Builder
        @QueryProjection
        public CountByGender(String gender, String choice, Long count) {
            this.gender = gender;
            this.choice = choice;
            this.count = count;
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CountByAge {
        private int age;
        private String choice;
        private Long count;

        @Builder
        @QueryProjection
        public CountByAge(int age, String choice, Long count) {
            this.age = age;
            this.choice = choice;
            this.count = count;
        }
    }
}

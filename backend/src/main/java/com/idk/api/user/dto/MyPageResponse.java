package com.idk.api.user.dto;

import com.idk.api.user.domain.entity.User;
import lombok.*;

public class MyPageResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UserInfo{
        private Long id;
        private String name;
        private String email;
        private int districtId;
        private String districtName;
        private String gender;
        private int age;

        public static UserInfo build(User user){
            return UserInfo.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .districtId(user.getDistrictCode().getId())
                    .districtName(user.getDistrictCode().getName())
                    .gender(user.getGender())
                    .age(user.getAge())
                    .build();

        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Rate{
        private Long id;
        private long ballotCount;
        private long correctCount;

        public static Rate build(Long id, long ballotCount, long correctCount){
            return Rate.builder()
                    .id(id)
                    .ballotCount(ballotCount)
                    .correctCount(correctCount)
                    .build();
        }

    }
}

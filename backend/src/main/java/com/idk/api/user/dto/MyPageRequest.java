package com.idk.api.user.dto;

import lombok.*;

public class MyPageRequest {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UserInfo{
        private int districtId;
        private String gender;
        private int age;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UserPassword{
        private String curPassword;
        private String newPassword;
    }
}

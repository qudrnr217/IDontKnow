package com.idk.api.mypage.dto;

import com.idk.api.user.domain.Age;
import lombok.*;

public class MyPageRequest {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UserInfo{
        private int districtId;
        private String gender;
        private Age age;
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

package com.idk.api.user.dto;

import com.idk.api.user.domain.entity.User;
import com.idk.api.user.security.token.Token;
import lombok.*;

public class UserResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class OnlyId{
        private Long userId;

        public static OnlyId build(User user){
            return OnlyId.builder()
                    .userId(user.getId())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Login {
        private Long userId;
        private String name;
        private int districtId;

        public static Login build(User user) {
            return Login.builder()
                    .userId(user.getId())
                    .name(user.getName())
                    .districtId(user.getDistrictCode().getId())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class LoginWithToken {
        private Long userId;
        private String name;
        private int districtId;
        private String accessToken;
        private String refreshToken;

        public static LoginWithToken build(User user,Token accessToken, Token refreshToken) {
            return LoginWithToken.builder()
                    .userId(user.getId())
                    .name(user.getName())
                    .districtId(user.getDistrictCode().getId())
                    .accessToken(accessToken.getToken())
                    .refreshToken(refreshToken.getToken())
                    .build();
        }
    }
}

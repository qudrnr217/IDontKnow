package com.idk.api.user.domain.dto;

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
        private String accessToken;

        public static Login build(User user,Token accessToken) {
            return Login.builder()
                    .userId(user.getId())
                    .name(user.getName())
                    .districtId(user.getDistrictCode().getId())
                    .accessToken(accessToken.getToken())
                    .build();
        }

    }
}

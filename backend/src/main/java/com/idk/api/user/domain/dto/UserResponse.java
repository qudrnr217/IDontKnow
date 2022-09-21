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
        private Long id;

        public static OnlyId build(User user){
            return OnlyId.builder()
                    .id(user.getId())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Login {
        private String name;
        private String accessToken;

        public static Login build(User user, Token accessToken) {
            return Login.builder()
                    .name(user.getName())
                    .accessToken(accessToken.getToken())
                    .build();
        }

    }
}

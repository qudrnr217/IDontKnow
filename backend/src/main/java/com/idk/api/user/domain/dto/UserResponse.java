package com.idk.api.user.domain.dto;

import com.idk.api.user.domain.entity.User;
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
}

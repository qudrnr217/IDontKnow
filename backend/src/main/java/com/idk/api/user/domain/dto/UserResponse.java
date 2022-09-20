package com.idk.api.user.domain.dto;

import com.idk.api.user.domain.entity.User;
import lombok.*;

public class UserResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UserInfoOnlyId{
        private Long id;

        public static UserInfoOnlyId build(User user){
            return UserInfoOnlyId.builder()
                    .id(user.getId())
                    .build();
        }
    }
}

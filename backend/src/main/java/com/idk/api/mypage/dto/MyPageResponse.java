package com.idk.api.mypage.dto;

import com.idk.api.user.domain.Age;
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
        private String gender;
        private Age age;

        public static UserInfo build(User user){
            return UserInfo.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .districtId(user.getDistrictCode().getId())
                    .gender(user.getGender())
                    .age(user.getAge())
                    .build();

        }
    }
}

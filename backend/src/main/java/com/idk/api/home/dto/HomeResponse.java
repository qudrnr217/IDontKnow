package com.idk.api.home.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.idk.api.home.domain.entity.Data;
import lombok.*;

import java.time.LocalDateTime;

public class HomeResponse {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Menu{
        private int menuId;
        private String menuName;
        private long orderQuantity;

        public static Menu build(Data datum){
            return Menu.builder()
                    .menuId(datum.getMenuCode().getId())
                    .menuName(datum.getMenuCode().getName())
                    .orderQuantity(datum.getOrderQuantity())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Hotspot{
        private String placeA;
        private String placeB;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd hh:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime updatedAt;

        public static Hotspot build(com.idk.api.home.domain.entity.Hotspot hotspot){
            return Hotspot.builder()
                    .placeA(hotspot.getFirstPlace())
                    .placeB(hotspot.getSecondPlace())
                    .updatedAt(hotspot.getCreatedAt())
                    .build();
        }
    }
}

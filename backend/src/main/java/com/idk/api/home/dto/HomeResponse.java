package com.idk.api.home.dto;

import com.idk.api.home.domain.entity.Data;
import lombok.*;

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
}

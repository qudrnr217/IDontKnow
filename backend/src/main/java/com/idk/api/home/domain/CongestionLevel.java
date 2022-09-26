package com.idk.api.home.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CongestionLevel {
    여유(0),
    보통(1),
    붐빔(2),
    매우_붐빔(3);

    private final int level;
}
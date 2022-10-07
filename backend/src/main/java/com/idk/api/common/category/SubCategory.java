package com.idk.api.common.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SubCategory {
    KOREAN("한식"),
    DINER("분식"),
    DESSERT("카페/디저트"),
    JAPANESE("돈까스/일식"),
    SASHIMI("회"),
    CHICKEN("치킨"),
    PIZZA("피자"),
    WESTERN("아시안/양식"),
    PORK("족발/보쌈"),
    SNACK("야식"),
    SOUP("찜탕"),
    LUNCHBOX("도시락"),
    FASTFOOD("패스트푸드"),
    HAIR("머리"),
    CLOTHES("옷"),
    SHOES("신발"),
    ACCESSORY("액세서리"),
    INDOOR("실내"),
    OUTDOOR("실외");

    private final String name;

    public static SubCategory fromString(String name) {
        for (SubCategory subCategory : SubCategory.values()) {
            if (subCategory.name.equals(name))
                return subCategory;
        }
        return null;
    }
}
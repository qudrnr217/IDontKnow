package com.idk.api.common.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    MENU("메뉴"),
    STYLE("스타일"),
    LOCATION("장소");

    private final String name;

    public static Category fromString(String name) {
        for(Category category : Category.values()){
            if(category.name.equals(name))
                return category;
        }
        return null;
    }

}

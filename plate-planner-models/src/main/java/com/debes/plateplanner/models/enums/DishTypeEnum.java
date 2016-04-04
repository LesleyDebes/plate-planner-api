package com.debes.plateplanner.models.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author lesley.debes
 */
public enum DishTypeEnum {
    APPETIZER("app"),
    DESSERT("des"),
    DRINK("drk"),
    ENTREE("ent"),
    SNACK("snk"),
    SIDE("sid"),
    SOUP("sop");

    private final String dishTypeValue;

    DishTypeEnum(String dishTypeValue) {
        this.dishTypeValue = dishTypeValue;
    }

    public String getDishTypeValue() {
        return this.dishTypeValue;
    }

    public static DishTypeEnum get(String dishTypeValue) {
        return Arrays.stream(DishTypeEnum.values())
                .filter(e -> StringUtils.equalsIgnoreCase(e.dishTypeValue, dishTypeValue))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unsupported type: " + dishTypeValue));
    }
}

package com.debes.plateplanner.models.enums;

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
}

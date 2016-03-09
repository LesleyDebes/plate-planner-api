package com.debes.plateplanner.models.enums;

/**
 * @author lesley.debes
 */
public enum MealTypeEnum {
    BREAKFAST("brk"),
    LUNCH("lun"),
    DINNER("din");

    private final String mealTypeValue;
    MealTypeEnum(String mealTypeValue) {
        this.mealTypeValue = mealTypeValue;
    }
    public String getMealTypeValue() {
        return this.mealTypeValue;
    }
}

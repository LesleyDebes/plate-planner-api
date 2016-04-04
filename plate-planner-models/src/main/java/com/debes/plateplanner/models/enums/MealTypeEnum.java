package com.debes.plateplanner.models.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

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

    public static MealTypeEnum get(String mealTypeValue) {
        return Arrays.stream(MealTypeEnum.values())
                .filter(e -> StringUtils.equalsIgnoreCase(e.mealTypeValue, mealTypeValue))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unsupported type: " + mealTypeValue));
    }
}

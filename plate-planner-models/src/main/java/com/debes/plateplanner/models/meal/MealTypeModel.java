package com.debes.plateplanner.models.meal;

import com.debes.plateplanner.models.enums.MealTypeEnum;

/**
 * @author lesley.debes
 */
public class MealTypeModel {
    //TODO:  ADD VALIDATION
    private MealTypeEnum mealType;
    private String mealTypeDescription;

    public MealTypeEnum getMealType() {
        return mealType;
    }

    public void setMealType(MealTypeEnum mealType) {
        this.mealType = mealType;
    }

    public String getMealTypeDescription() {
        return mealTypeDescription;
    }

    public void setMealTypeDescription(String mealTypeDescription) {
        this.mealTypeDescription = mealTypeDescription;
    }
}

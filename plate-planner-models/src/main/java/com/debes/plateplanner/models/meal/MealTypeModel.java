package com.debes.plateplanner.models.meal;

/**
 * @author lesley.debes
 */
public class MealTypeModel {
    //TODO:  ADD VALIDATION
    private String idMealType;
    private String mealType;

    public String getIdMealType() {
        return idMealType;
    }

    public void setIdMealType(String idMealType) {
        this.idMealType = idMealType;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
}

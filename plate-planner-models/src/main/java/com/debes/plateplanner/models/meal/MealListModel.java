package com.debes.plateplanner.models.meal;

import com.debes.plateplanner.models.BaseModel;

import java.util.List;

/**
 * @author lesley.debes
 */
public class MealListModel extends BaseModel {
    //TODO:  ADD VALIDATION
    List<MealModel> mealList;

    public List<MealModel> getMealList() {
        return mealList;
    }

    public void setMealList(List<MealModel> mealList) {
        this.mealList = mealList;
    }
}

package com.debes.plateplanner.models.meal;

import com.debes.plateplanner.models.BaseModel;

import java.util.List;

/**
 * @author lesley.debes
 */
public class MealTypeListModel extends BaseModel {
    private List<MealTypeModel> mealTypeList;

    public List<MealTypeModel> getMealTypeList() {
        return mealTypeList;
    }

    public void setMealTypeList(List<MealTypeModel> mealTypeList) {
        this.mealTypeList = mealTypeList;
    }
}

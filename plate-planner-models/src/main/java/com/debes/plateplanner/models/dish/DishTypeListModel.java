package com.debes.plateplanner.models.dish;

import com.debes.plateplanner.models.BaseModel;

import java.util.List;

/**
 * @author lesley.debes
 */
public class DishTypeListModel extends BaseModel {
    //TODO:  ADD VALIDATION
    private List<DishTypeModel> dishTypeList;

    public List<DishTypeModel> getDishTypeList() {
        return dishTypeList;
    }

    public void setDishTypeList(List<DishTypeModel> dishTypeList) {
        this.dishTypeList = dishTypeList;
    }
}

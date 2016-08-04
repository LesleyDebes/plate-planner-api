package com.debes.plateplanner.models.dish;

import com.debes.plateplanner.models.BaseModel;

import java.util.List;

/**
 * @author lesley.debes
 */
public class DishListModel extends BaseModel {
    private List<DishModel> dishModelList;

    public List<DishModel> getDishModelList() {
        return dishModelList;
    }

    public void setDishModelList(List<DishModel> dishModelList) {
        this.dishModelList = dishModelList;
    }
}

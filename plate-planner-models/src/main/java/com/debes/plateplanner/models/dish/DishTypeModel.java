package com.debes.plateplanner.models.dish;

import com.debes.plateplanner.models.enums.DishTypeEnum;

/**
 * @author lesley.debes
 */
public class DishTypeModel {
    private DishTypeEnum dishType;
    private String dishTypeDescription;

    public DishTypeEnum getDishType() {
        return dishType;
    }

    public void setDishType(DishTypeEnum dishType) {
        this.dishType = dishType;
    }

    public String getDishTypeDescription() {
        return dishTypeDescription;
    }

    public void setDishTypeDescription(String dishTypeDescription) {
        this.dishTypeDescription = dishTypeDescription;
    }
}

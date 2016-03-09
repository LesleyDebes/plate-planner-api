package com.debes.plateplanner.models.dish;

import com.debes.plateplanner.models.BaseModel;

/**
 * @author lesley.debes
 */
public class DishModel extends BaseModel {
    //TODO: ADD VALIDATION
    private Integer idDish;
    private String idDishType;
    private String dishName;
    private Integer idMeal;
    private Integer idRecipe;
    private short orderSequence;
    private String createTimestamp;
    private String updateTimestamp;

    public Integer getIdDish() {
        return idDish;
    }

    public void setIdDish(Integer idDish) {
        this.idDish = idDish;
    }

    public String getIdDishType() {
        return idDishType;
    }

    public void setIdDishType(String idDishType) {
        this.idDishType = idDishType;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Integer getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(Integer idMeal) {
        this.idMeal = idMeal;
    }

    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    public short getOrderSequence() {
        return orderSequence;
    }

    public void setOrderSequence(short orderSequence) {
        this.orderSequence = orderSequence;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}

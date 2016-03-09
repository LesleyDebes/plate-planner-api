package com.debes.plateplanner.models.meal;

import com.debes.plateplanner.models.BaseModel;

/**
 * @author lesley.debes
 */
public class MealModel extends BaseModel {
    //TODO: ADD VALIDATION
    private Integer idMeal;
    private String idMealType;
    private String mealName;
    private String mealDate;
    private short orderSequence;
    private String createTimestamp;
    private String updateTimestamp;

    public Integer getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(Integer idMeal) {
        this.idMeal = idMeal;
    }

    public String getIdMealType() {
        return idMealType;
    }

    public void setIdMealType(String idMealType) {
        this.idMealType = idMealType;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealDate() {
        return mealDate;
    }

    public void setMealDate(String mealDate) {
        this.mealDate = mealDate;
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

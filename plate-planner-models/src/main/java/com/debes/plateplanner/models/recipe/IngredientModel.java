package com.debes.plateplanner.models.recipe;

import com.debes.plateplanner.models.BaseModel;

/**
 * @author lesley.debes
 */
public class IngredientModel extends BaseModel {
    //TODO: ADD VALIDATION
    private Integer idRecipeIngredient;
    private String ingredientName;
    private String idMeasurement;
    private String ingredientMeasurementAmount;
    private short orderSequence;
    private Integer idRecipe;

    public Integer getIdRecipeIngredient() {
        return idRecipeIngredient;
    }

    public void setIdRecipeIngredient(Integer idRecipeIngredient) {
        this.idRecipeIngredient = idRecipeIngredient;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIdMeasurement() {
        return idMeasurement;
    }

    public void setIdMeasurement(String idMeasurement) {
        this.idMeasurement = idMeasurement;
    }

    public String getIngredientMeasurementAmount() {
        return ingredientMeasurementAmount;
    }

    public void setIngredientMeasurementAmount(String ingredientMeasurementAmount) {
        this.ingredientMeasurementAmount = ingredientMeasurementAmount;
    }

    public short getOrderSequence() {
        return orderSequence;
    }

    public void setOrderSequence(short orderSequence) {
        this.orderSequence = orderSequence;
    }

    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }
}

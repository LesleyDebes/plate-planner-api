package com.debes.plateplanner.models.recipe;

import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.enums.MeasurementEnum;

/**
 * @author lesley.debes
 */
public class RecipeIngredientModel extends BaseModel {
    //TODO: ADD VALIDATION
    private Integer idRecipeIngredient;
    private String ingredientName;
    private MeasurementEnum measurement;
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

    public MeasurementEnum getMeasurement() {
        return measurement;
    }

    public void setMeasurement(MeasurementEnum measurement) {
        this.measurement = measurement;
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

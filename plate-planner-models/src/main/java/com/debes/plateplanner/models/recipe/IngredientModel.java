package com.debes.plateplanner.models.recipe;

import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.enums.MeasurementEnum;

/**
 * @author lesley.debes
 */
public class IngredientModel extends BaseModel {
    //TODO: ADD VALIDATION
    private Integer idIngredient;
    private String ingredientName;
    private MeasurementEnum measurement;
    private String ingredientMeasurementAmount;
    private short orderSequence;

    public Integer getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Integer idIngredient) {
        this.idIngredient = idIngredient;
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

}

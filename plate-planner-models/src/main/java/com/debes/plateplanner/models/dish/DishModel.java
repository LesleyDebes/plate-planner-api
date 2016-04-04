package com.debes.plateplanner.models.dish;

import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.enums.DishTypeEnum;
import com.debes.plateplanner.util.TimestampDeserializer;
import com.debes.plateplanner.util.TimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Timestamp;

/**
 * @author lesley.debes
 */
public class DishModel extends BaseModel {
    //TODO: ADD VALIDATION
    private Integer idDish;
    private DishTypeEnum dishType;
    private String dishName;
    private Integer idMeal;
    private Integer idRecipe;
    private short orderSequence;
    private Timestamp createTimestamp;
    private Timestamp updateTimestamp;

    public Integer getIdDish() {
        return idDish;
    }

    public void setIdDish(Integer idDish) {
        this.idDish = idDish;
    }

    public DishTypeEnum getDishType() {
        return dishType;
    }

    public void setDishType(DishTypeEnum dishType) {
        this.dishType = dishType;
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

    @JsonSerialize(using = TimestampSerializer.class)
    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    @JsonDeserialize(using = TimestampDeserializer.class)
    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    @JsonSerialize(using = TimestampSerializer.class)
    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    @JsonDeserialize(using = TimestampDeserializer.class)
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}

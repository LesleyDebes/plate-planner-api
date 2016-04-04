package com.debes.plateplanner.models.meal;

import com.debes.plateplanner.models.BaseModel;
import com.debes.plateplanner.models.enums.MealTypeEnum;
import com.debes.plateplanner.util.DateDeserializer;
import com.debes.plateplanner.util.DateSerializer;
import com.debes.plateplanner.util.TimestampDeserializer;
import com.debes.plateplanner.util.TimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author lesley.debes
 */
public class MealModel extends BaseModel {
    //TODO: ADD VALIDATION
    private Integer idMeal;
    private MealTypeEnum mealType;
    private String mealName;
    private Date mealDate;
    private short orderSequence;
    private Timestamp createTimestamp;
    private Timestamp updateTimestamp;

    public Integer getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(Integer idMeal) {
        this.idMeal = idMeal;
    }

    public MealTypeEnum getMealType() {
        return mealType;
    }

    public void setMealType(MealTypeEnum mealType) {
        this.mealType = mealType;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Date getMealDate() {
        return mealDate;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public void setMealDate(Date mealDate) {
        this.mealDate = mealDate;
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

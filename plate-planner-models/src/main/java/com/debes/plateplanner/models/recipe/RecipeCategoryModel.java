package com.debes.plateplanner.models.recipe;

import com.debes.plateplanner.models.BaseModel;

import java.sql.Timestamp;

/**
 * @author lesley.debes
 */
public class RecipeCategoryModel extends BaseModel {
    //TODO:  ADD VALIDATION
    private Integer idRecipeCategory;
    private String recipeCategory;
    private short orderSequence;
    private Timestamp createTimestamp;
    private Timestamp updateTimestamp;

    public Integer getIdRecipeCategory() {
        return idRecipeCategory;
    }

    public void setIdRecipeCategory(Integer idRecipeCategory) {
        this.idRecipeCategory = idRecipeCategory;
    }

    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public short getOrderSequence() {
        return orderSequence;
    }

    public void setOrderSequence(short orderSequence) {
        this.orderSequence = orderSequence;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}

package com.debes.plateplanner.models.recipe;

import com.debes.plateplanner.models.BaseModel;

/**
 * @author lesley.debes
 */
public class RecipeCategoryModel extends BaseModel {
    //TODO:  ADD VALIDATION
    private Integer idRecipeCategory;
    private String recipeCategory;
    private short orderSequence;
    private String createTimestamp;
    private String updateTimestamp;

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

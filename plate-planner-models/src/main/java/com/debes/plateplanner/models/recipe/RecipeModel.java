package com.debes.plateplanner.models.recipe;

import com.debes.plateplanner.models.BaseModel;

import java.sql.Timestamp;

/**
 * @author lesley.debes
 */
public class RecipeModel extends BaseModel {
    //TODO: ADD VALIDATION
    private Integer idRecipe;
    private String recipeName;
    private String recipeSource;
    private Timestamp createTimestamp;
    private Timestamp updateTimestamp;

    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeSource() {
        return recipeSource;
    }

    public void setRecipeSource(String recipeSource) {
        this.recipeSource = recipeSource;
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

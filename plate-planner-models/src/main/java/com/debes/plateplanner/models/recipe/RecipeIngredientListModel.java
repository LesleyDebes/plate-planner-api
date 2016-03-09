package com.debes.plateplanner.models.recipe;

import com.debes.plateplanner.models.BaseModel;

import java.util.List;

/**
 * @author lesley.debes
 */
public class RecipeIngredientListModel extends BaseModel {
    //TODO:  ADD VALIDATION
    private Integer idRecipe;
    private List<IngredientModel> ingredientModelList;

    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    public List<IngredientModel> getIngredientModelList() {
        return ingredientModelList;
    }

    public void setIngredientModelList(List<IngredientModel> ingredientModelList) {
        this.ingredientModelList = ingredientModelList;
    }
}

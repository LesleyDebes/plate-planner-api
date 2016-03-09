package com.debes.plateplanner.models.recipe;

import com.debes.plateplanner.models.BaseModel;

import java.util.List;

/**
 * @author lesley.debes
 */
public class RecipeListModel extends BaseModel {
    public List<RecipeModel> recipeModelList;

    public List<RecipeModel> getRecipeModelList() {
        return recipeModelList;
    }

    public void setRecipeModelList(List<RecipeModel> recipeModelList) {
        this.recipeModelList = recipeModelList;
    }
}

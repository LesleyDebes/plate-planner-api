package com.debes.plateplanner.models.recipe;

import com.debes.plateplanner.models.BaseModel;

import java.util.List;

/**
 * @author lesley.debes
 */
public class RecipeCategoryListModel extends BaseModel {
    //TODO:  ADD VALIDATION
    private List<RecipeCategoryModel> recipeCategoryList;

    public List<RecipeCategoryModel> getRecipeCategoryList() {
        return recipeCategoryList;
    }

    public void setRecipeCategoryList(List<RecipeCategoryModel> recipeCategoryList) {
        this.recipeCategoryList = recipeCategoryList;
    }
}

package com.debes.plateplanner.dao.recipe;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author lesley.debes
 */
public class RecipeHasCategoryPK implements Serializable {
    private Integer idRecipe;
    private Integer idRecipeCategory;

    @Column(name = "id_recipe", nullable = false)
    @Id
    public Serializable getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    @Column(name = "id_recipe_category", nullable = false)
    @Id
    public Serializable getIdRecipeCategory() {
        return idRecipeCategory;
    }

    public void setIdRecipeCategory(Integer idRecipeCategory) {
        this.idRecipeCategory = idRecipeCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        RecipeHasCategoryPK that = (RecipeHasCategoryPK) o;

        if (idRecipe != null ? !idRecipe.equals(that.idRecipe) : that.idRecipe != null) { return false; }
        if (idRecipeCategory != null
            ? !idRecipeCategory.equals(that.idRecipeCategory)
            : that.idRecipeCategory != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRecipe != null ? idRecipe.hashCode() : 0;
        result = 31 * result + (idRecipeCategory != null ? idRecipeCategory.hashCode() : 0);
        return result;
    }
}

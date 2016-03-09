package com.debes.plateplanner.dao.recipe;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author lesley.debes
 */
@Entity
public class Recipe {
    private Integer idRecipe;
    private String recipeName;
    private String recipeSource;
    private Timestamp createTimestamp;
    private Timestamp updateTimestamp;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe", nullable = false)
    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    @Basic
    @Column(name = "recipe_name", nullable = false, length = -1)
    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    @Basic
    @Column(name = "recipe_source", nullable = false, length = -1)
    public String getRecipeSource() {
        return recipeSource;
    }

    public void setRecipeSource(String recipeSource) {
        this.recipeSource = recipeSource;
    }

    @Basic
    @Column(name = "create_timestamp", nullable = false)
    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    @Basic
    @Column(name = "update_timestamp", nullable = true)
    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Recipe recipe = (Recipe) o;

        if (idRecipe != null ? !idRecipe.equals(recipe.idRecipe) : recipe.idRecipe != null) { return false; }
        if (recipeName != null ? !recipeName.equals(recipe.recipeName) : recipe.recipeName != null) { return false; }
        if (recipeSource != null ? !recipeSource.equals(recipe.recipeSource) : recipe.recipeSource != null) {
            return false;
        }
        if (createTimestamp != null
            ? !createTimestamp.equals(recipe.createTimestamp)
            : recipe.createTimestamp != null) {
            return false;
        }
        if (updateTimestamp != null
            ? !updateTimestamp.equals(recipe.updateTimestamp)
            : recipe.updateTimestamp != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRecipe != null ? idRecipe.hashCode() : 0;
        result = 31 * result + (recipeName != null ? recipeName.hashCode() : 0);
        result = 31 * result + (recipeSource != null ? recipeSource.hashCode() : 0);
        result = 31 * result + (createTimestamp != null ? createTimestamp.hashCode() : 0);
        result = 31 * result + (updateTimestamp != null ? updateTimestamp.hashCode() : 0);
        return result;
    }
}

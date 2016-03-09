package com.debes.plateplanner.dao.recipe;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author lesley.debes
 */
@Entity
@Table(name = "recipe_has_category", schema = "public", catalog = "plateplanner")
@IdClass(RecipeHasCategoryPK.class)
public class RecipeHasCategory {
    private Integer idRecipe;
    private Integer idRecipeCategory;
    private Timestamp createTimestamp;
    private Timestamp updateTimestamp;

    @Id
    @Column(name = "id_recipe", nullable = false)
    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    @Id
    @Column(name = "id_recipe_category", nullable = false)
    public Integer getIdRecipeCategory() {
        return idRecipeCategory;
    }

    public void setIdRecipeCategory(Integer idRecipeCategory) {
        this.idRecipeCategory = idRecipeCategory;
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

        RecipeHasCategory that = (RecipeHasCategory) o;

        if (idRecipe != null ? !idRecipe.equals(that.idRecipe) : that.idRecipe != null) { return false; }
        if (idRecipeCategory != null
            ? !idRecipeCategory.equals(that.idRecipeCategory)
            : that.idRecipeCategory != null) {
            return false;
        }
        if (createTimestamp != null ? !createTimestamp.equals(that.createTimestamp) : that.createTimestamp != null) {
            return false;
        }
        if (updateTimestamp != null ? !updateTimestamp.equals(that.updateTimestamp) : that.updateTimestamp != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRecipe != null ? idRecipe.hashCode() : 0;
        result = 31 * result + (idRecipeCategory != null ? idRecipeCategory.hashCode() : 0);
        result = 31 * result + (createTimestamp != null ? createTimestamp.hashCode() : 0);
        result = 31 * result + (updateTimestamp != null ? updateTimestamp.hashCode() : 0);
        return result;
    }
}

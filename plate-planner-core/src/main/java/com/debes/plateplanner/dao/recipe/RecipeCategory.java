package com.debes.plateplanner.dao.recipe;

import javax.persistence.*;;
import java.sql.Timestamp;

/**
 * @author lesley.debes
 */
@Entity
@Table(name = "recipe_category", schema = "public", catalog = "plateplanner")
public class RecipeCategory {
    private Integer idRecipeCategory;
    private String recipeCategory;
    private short orderSequence;
    private Timestamp createTimestamp;
    private Timestamp updateTimestamp;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe_category", nullable = false)
    public Integer getIdRecipeCategory() {
        return idRecipeCategory;
    }

    public void setIdRecipeCategory(Integer idRecipeCategory) {
        this.idRecipeCategory = idRecipeCategory;
    }

    @Basic
    @Column(name = "recipe_category", nullable = false, length = -1)
    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    @Basic
    @Column(name = "order_sequence", nullable = false)
    public short getOrderSequence() {
        return orderSequence;
    }

    public void setOrderSequence(short orderSequence) {
        this.orderSequence = orderSequence;
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

        RecipeCategory that = (RecipeCategory) o;

        if (orderSequence != that.orderSequence) { return false; }
        if (idRecipeCategory != null
            ? !idRecipeCategory.equals(that.idRecipeCategory)
            : that.idRecipeCategory != null) {
            return false;
        }
        if (recipeCategory != null ? !recipeCategory.equals(that.recipeCategory) : that.recipeCategory != null) {
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
        int result = idRecipeCategory != null ? idRecipeCategory.hashCode() : 0;
        result = 31 * result + (recipeCategory != null ? recipeCategory.hashCode() : 0);
        result = 31 * result + (int) orderSequence;
        result = 31 * result + (createTimestamp != null ? createTimestamp.hashCode() : 0);
        result = 31 * result + (updateTimestamp != null ? updateTimestamp.hashCode() : 0);
        return result;
    }
}

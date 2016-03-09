package com.debes.plateplanner.dao.recipe;

import javax.persistence.*;

/**
 * @author lesley.debes
 */
@Entity
@Table(name = "recipe_ingredient", schema = "public", catalog = "plateplanner")
public class RecipeIngredient {
    private Integer idRecipeIngredient;
    private String ingredientName;
    private String idMeasurement;
    private String ingredientMeasurementAmount;
    private short orderSequence;
    private Integer idRecipe;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe_ingredient", nullable = false)
    public Integer getIdRecipeIngredient() {
        return idRecipeIngredient;
    }

    public void setIdRecipeIngredient(Integer idRecipeIngredient) {
        this.idRecipeIngredient = idRecipeIngredient;
    }

    @Basic
    @Column(name = "ingredient_name", nullable = false, length = -1)
    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Basic
    @Column(name = "id_measurement", nullable = false, length = 3)
    public String getIdMeasurement() {
        return idMeasurement;
    }

    public void setIdMeasurement(String idMeasurement) {
        this.idMeasurement = idMeasurement;
    }

    @Basic
    @Column(name = "ingredient_measurement_amount", nullable = false, length = -1)
    public String getIngredientMeasurementAmount() {
        return ingredientMeasurementAmount;
    }

    public void setIngredientMeasurementAmount(String ingredientMeasurementAmount) {
        this.ingredientMeasurementAmount = ingredientMeasurementAmount;
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
    @Column(name = "id_recipe", nullable = false)
    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        RecipeIngredient that = (RecipeIngredient) o;

        if (orderSequence != that.orderSequence) { return false; }
        if (idRecipeIngredient != null
            ? !idRecipeIngredient.equals(that.idRecipeIngredient)
            : that.idRecipeIngredient != null) { return false; }
        if (ingredientName != null ? !ingredientName.equals(that.ingredientName) : that.ingredientName != null) {
            return false;
        }
        if (idMeasurement != null ? !idMeasurement.equals(that.idMeasurement) : that.idMeasurement != null) {
            return false;
        }
        if (ingredientMeasurementAmount != null
            ? !ingredientMeasurementAmount.equals(that.ingredientMeasurementAmount)
            : that.ingredientMeasurementAmount != null) { return false; }
        if (idRecipe != null ? !idRecipe.equals(that.idRecipe) : that.idRecipe != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRecipeIngredient != null ? idRecipeIngredient.hashCode() : 0;
        result = 31 * result + (ingredientName != null ? ingredientName.hashCode() : 0);
        result = 31 * result + (idMeasurement != null ? idMeasurement.hashCode() : 0);
        result = 31 * result + (ingredientMeasurementAmount != null ? ingredientMeasurementAmount.hashCode() : 0);
        result = 31 * result + (int) orderSequence;
        result = 31 * result + (idRecipe != null ? idRecipe.hashCode() : 0);
        return result;
    }
}
package com.debes.plateplanner.dao.dish;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author lesley.debes
 */
@Entity
public class Dish {
    private Integer idDish;
    private String idDishType;
    private String dishName;
    private Integer idMeal;
    private Integer idRecipe;
    private short orderSequence;
    private Timestamp createTimestamp;
    private Timestamp updateTimestamp;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dish", nullable = false)
    public Integer getIdDish() {
        return idDish;
    }

    public void setIdDish(Integer idDish) {
        this.idDish = idDish;
    }

    @Basic
    @Column(name = "id_dish_type", nullable = false, length = 3)
    public String getIdDishType() {
        return idDishType;
    }

    public void setIdDishType(String idDishType) {
        this.idDishType = idDishType;
    }

    @Basic
    @Column(name = "dish_name", nullable = false, length = -1)
    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    @Basic
    @Column(name = "id_meal", nullable = false)
    public Integer getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(Integer idMeal) {
        this.idMeal = idMeal;
    }

    @Basic
    @Column(name = "id_recipe", nullable = false)
    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
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

        Dish dish = (Dish) o;

        if (orderSequence != dish.orderSequence) { return false; }
        if (idDish != null ? !idDish.equals(dish.idDish) : dish.idDish != null) { return false; }
        if (idDishType != null ? !idDishType.equals(dish.idDishType) : dish.idDishType != null) { return false; }
        if (dishName != null ? !dishName.equals(dish.dishName) : dish.dishName != null) { return false; }
        if (idMeal != null ? !idMeal.equals(dish.idMeal) : dish.idMeal != null) { return false; }
        if (idRecipe != null ? !idRecipe.equals(dish.idRecipe) : dish.idRecipe != null) { return false; }
        if (createTimestamp != null ? !createTimestamp.equals(dish.createTimestamp) : dish.createTimestamp != null) {
            return false;
        }
        if (updateTimestamp != null ? !updateTimestamp.equals(dish.updateTimestamp) : dish.updateTimestamp != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDish != null ? idDish.hashCode() : 0;
        result = 31 * result + (idDishType != null ? idDishType.hashCode() : 0);
        result = 31 * result + (dishName != null ? dishName.hashCode() : 0);
        result = 31 * result + (idMeal != null ? idMeal.hashCode() : 0);
        result = 31 * result + (idRecipe != null ? idRecipe.hashCode() : 0);
        result = 31 * result + (int) orderSequence;
        result = 31 * result + (createTimestamp != null ? createTimestamp.hashCode() : 0);
        result = 31 * result + (updateTimestamp != null ? updateTimestamp.hashCode() : 0);
        return result;
    }
}

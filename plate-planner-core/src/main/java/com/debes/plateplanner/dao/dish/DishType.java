package com.debes.plateplanner.dao.dish;

import javax.persistence.*;

/**
 * @author lesley.debes
 */
@Entity
@Table(name = "dish_type", schema = "public", catalog = "plateplanner")
public class DishType {
    private String idDishType;
    private String dishType;
    private short orderSequence;

    @Id
    @Column(name = "id_dish_type", nullable = false, length = 3)
    public String getIdDishType() {
        return idDishType;
    }

    public void setIdDishType(String idDishType) {
        this.idDishType = idDishType;
    }

    @Basic
    @Column(name = "dish_type", nullable = false, length = -1)
    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    @Basic
    @Column(name = "order_sequence", nullable = false)
    public short getOrderSequence() {
        return orderSequence;
    }

    public void setOrderSequence(short orderSequence) {
        this.orderSequence = orderSequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        DishType dishType1 = (DishType) o;

        if (orderSequence != dishType1.orderSequence) { return false; }
        if (idDishType != null ? !idDishType.equals(dishType1.idDishType) : dishType1.idDishType != null) {
            return false;
        }
        if (dishType != null ? !dishType.equals(dishType1.dishType) : dishType1.dishType != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDishType != null ? idDishType.hashCode() : 0;
        result = 31 * result + (dishType != null ? dishType.hashCode() : 0);
        result = 31 * result + (int) orderSequence;
        return result;
    }
}

package com.debes.plateplanner.dao.meal;

import javax.persistence.*;

/**
 * @author lesley.debes
 */
@Entity
@Table(name = "meal_type", schema = "public", catalog = "plateplanner")
public class MealType {
    private String idMealType;
    private String mealType;
    private short orderSequence;

    @Id
    @Column(name = "id_meal_type", nullable = false, length = 3)
    public String getIdMealType() {
        return idMealType;
    }

    public void setIdMealType(String idMealType) {
        this.idMealType = idMealType;
    }

    @Basic
    @Column(name = "meal_type", nullable = false, length = -1)
    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
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

        MealType mealType1 = (MealType) o;

        if (orderSequence != mealType1.orderSequence) { return false; }
        if (idMealType != null ? !idMealType.equals(mealType1.idMealType) : mealType1.idMealType != null) {
            return false;
        }
        if (mealType != null ? !mealType.equals(mealType1.mealType) : mealType1.mealType != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMealType != null ? idMealType.hashCode() : 0;
        result = 31 * result + (mealType != null ? mealType.hashCode() : 0);
        result = 31 * result + (int) orderSequence;
        return result;
    }
}

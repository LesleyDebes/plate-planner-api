package com.debes.plateplanner.dao.meal;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author lesley.debes
 */
@Entity
public class Meal {
    private Integer idMeal;
    private String idMealType;
    private String mealName;
    private Date mealDate;
    private short orderSequence;
    private Timestamp createTimestamp;
    private Timestamp updateTimestamp;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_meal", nullable = false)
    public Integer getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(Integer idMeal) {
        this.idMeal = idMeal;
    }

    @Basic
    @Column(name = "id_meal_type", nullable = false, length = 3)
    public String getIdMealType() {
        return idMealType;
    }

    public void setIdMealType(String idMealType) {
        this.idMealType = idMealType;
    }

    @Basic
    @Column(name = "meal_name", nullable = false, length = -1)
    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    @Basic
    @Column(name = "meal_date", nullable = false)
    public Date getMealDate() {
        return mealDate;
    }

    public void setMealDate(Date mealDate) {
        this.mealDate = mealDate;
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

        Meal meal = (Meal) o;

        if (orderSequence != meal.orderSequence) { return false; }
        if (idMeal != null ? !idMeal.equals(meal.idMeal) : meal.idMeal != null) { return false; }
        if (idMealType != null ? !idMealType.equals(meal.idMealType) : meal.idMealType != null) { return false; }
        if (mealName != null ? !mealName.equals(meal.mealName) : meal.mealName != null) { return false; }
        if (mealDate != null ? !mealDate.equals(meal.mealDate) : meal.mealDate != null) { return false; }
        if (createTimestamp != null ? !createTimestamp.equals(meal.createTimestamp) : meal.createTimestamp != null) {
            return false;
        }
        if (updateTimestamp != null ? !updateTimestamp.equals(meal.updateTimestamp) : meal.updateTimestamp != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMeal != null ? idMeal.hashCode() : 0;
        result = 31 * result + (idMealType != null ? idMealType.hashCode() : 0);
        result = 31 * result + (mealName != null ? mealName.hashCode() : 0);
        result = 31 * result + (mealDate != null ? mealDate.hashCode() : 0);
        result = 31 * result + (int) orderSequence;
        result = 31 * result + (createTimestamp != null ? createTimestamp.hashCode() : 0);
        result = 31 * result + (updateTimestamp != null ? updateTimestamp.hashCode() : 0);
        return result;
    }
}

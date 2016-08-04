package com.debes.plateplanner.dao.dish.repository;

import com.debes.plateplanner.dao.dish.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lesley.debes
 */

public interface DishRepository extends JpaRepository<Dish, Integer> {
    Dish findByIdMealAndIdDish(Integer idMeal, Integer idDish);
    void deleteByIdMealAndIdDish(Integer idMeal, Integer idDish);
    List<Dish> findByIdMealOrderByOrderSequenceDesc(Integer idMeal);
}

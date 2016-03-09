package com.debes.plateplanner.dao.dish.repository;

import com.debes.plateplanner.dao.dish.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lesley.debes
 */
@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    void deleteByIdMealAndIdDish(Integer idMeal, Integer idDish);
}

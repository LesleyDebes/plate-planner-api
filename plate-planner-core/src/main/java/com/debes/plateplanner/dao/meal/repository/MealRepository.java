package com.debes.plateplanner.dao.meal.repository;

import com.debes.plateplanner.dao.meal.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lesley.debes
 */

public interface MealRepository extends JpaRepository<Meal, Integer> {

}

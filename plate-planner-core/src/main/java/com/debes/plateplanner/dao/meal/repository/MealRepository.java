package com.debes.plateplanner.dao.meal.repository;

import com.debes.plateplanner.dao.meal.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lesley.debes
 */
@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

}

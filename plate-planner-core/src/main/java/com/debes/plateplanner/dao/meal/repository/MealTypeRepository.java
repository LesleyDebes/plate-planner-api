package com.debes.plateplanner.dao.meal.repository;

import com.debes.plateplanner.dao.meal.MealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lesley.debes
 */
@Repository
public interface MealTypeRepository extends JpaRepository<MealType, String> {
    List<MealType> findAllByOrderByOrderSequence();
}

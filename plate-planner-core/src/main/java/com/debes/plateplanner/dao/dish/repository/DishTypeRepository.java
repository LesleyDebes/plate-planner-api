package com.debes.plateplanner.dao.dish.repository;

import com.debes.plateplanner.dao.dish.DishType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lesley.debes
 */
@Repository
public interface DishTypeRepository extends JpaRepository<DishType, String> {

    List<DishType> findAllByOrderByOrderSequence();

}

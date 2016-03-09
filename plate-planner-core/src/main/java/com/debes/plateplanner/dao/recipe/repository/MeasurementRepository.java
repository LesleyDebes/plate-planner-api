package com.debes.plateplanner.dao.recipe.repository;

import com.debes.plateplanner.dao.recipe.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lesley.debes
 */
@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

}

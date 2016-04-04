package com.debes.plateplanner.dao.measurement.repository;

import com.debes.plateplanner.dao.measurement.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lesley.debes
 */
@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, String> {
    List<Measurement> findAllByOrderByOrderSequence();
}

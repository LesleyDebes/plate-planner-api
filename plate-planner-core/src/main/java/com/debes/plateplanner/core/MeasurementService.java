package com.debes.plateplanner.core;

import com.debes.plateplanner.dao.measurement.Measurement;
import com.debes.plateplanner.dao.measurement.repository.MeasurementRepository;
import com.debes.plateplanner.models.enums.MeasurementEnum;
import com.debes.plateplanner.models.enums.ModelStatusEnum;
import com.debes.plateplanner.models.recipe.MeasurementListModel;
import com.debes.plateplanner.models.recipe.MeasurementModel;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lesley.debes
 */
@Service
public class MeasurementService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MeasurementRepository measurementRepository;

    public MeasurementListModel getMeasurements() {
        MeasurementListModel measurementListModel = new MeasurementListModel();
        try {
            List<Measurement> measurementList = measurementRepository.findAllByOrderByOrderSequence();
            if (CollectionUtils.isNotEmpty(measurementList)) {
                List<MeasurementModel> measurementModelList = new ArrayList<>();
                for (Measurement measurement : measurementList) {
                    MeasurementModel measurementModel = new MeasurementModel();
                    measurementModel.setMeasurement(MeasurementEnum.get(measurement.getIdMeasurement()));
                    measurementModel.setMeasurementDescription(measurement.getMeasurement());
                    measurementModelList.add(measurementModel);
                }
                measurementListModel.setMeasurementList(measurementModelList);
                measurementListModel.setModelStatusEnum(ModelStatusEnum.SUCCESS);
            } else {
                logger.error("No measurements found.");
                measurementListModel.setMeasurementList(new ArrayList<>());
                measurementListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
                measurementListModel.setMessage("No measurement options found.");
            }
        } catch (Exception e) {
            logger.error("There was an error retrieving measurements: ", e);
            measurementListModel.setMeasurementList(new ArrayList<>());
            measurementListModel.setModelStatusEnum(ModelStatusEnum.ERROR);
            measurementListModel.setMessage("There was an error retrieving the list of measurement options.");
        }
        return measurementListModel;
    }
}

package com.debes.plateplanner.models.recipe;

import com.debes.plateplanner.models.enums.MeasurementEnum;

/**
 * @author lesley.debes
 */
public class MeasurementModel  {
    //TODO:  ADD VALIDATION
    private MeasurementEnum measurement;
    private String measurementDescription;

    public MeasurementEnum getMeasurement() {
        return measurement;
    }

    public void setMeasurement(MeasurementEnum measurement) {
        this.measurement = measurement;
    }

    public String getMeasurementDescription() {
        return measurementDescription;
    }

    public void setMeasurementDescription(String measurementDescription) {
        this.measurementDescription = measurementDescription;
    }
}

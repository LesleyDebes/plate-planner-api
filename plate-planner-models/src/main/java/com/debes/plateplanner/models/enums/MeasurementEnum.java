package com.debes.plateplanner.models.enums;

/**
 * @author lesley.debes
 */
public enum MeasurementEnum {
    BAG("bag"),
    BOX("box"),
    CAN("can"),
    CONTAINER("con"),
    CUP("cup"),
    EACH("eac"),
    JAR("jar"),
    PACKAGE("pkg"),
    POUND("pnd"),
    TEASPOON("tsp"),
    TABLESPOON("tbp"),
    TUBE("tube");

    private final String measurementValue;
    MeasurementEnum(String measurementValue) {
        this.measurementValue = measurementValue;
    }

    public String getMeasurementValue() {
        return this.measurementValue;
    }
}

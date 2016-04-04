package com.debes.plateplanner.models.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

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
    TUBE("tub");

    private final String measurementValue;

    MeasurementEnum(String measurementValue) {
        this.measurementValue = measurementValue;
    }

    public String getMeasurementValue() {
        return this.measurementValue;
    }

    public static MeasurementEnum get(String measurementValue) {
        /*for(MeasurementEnum measurementEnum : MeasurementEnum.values()) {
            if (StringUtils.equalsIgnoreCase(measurementEnum.getMeasurementValue(), measurementValue)) {
                return measurementEnum;
            }
        }
        throw new IllegalStateException("Unsupported type: " + measurementValue);*/
        return Arrays.stream(MeasurementEnum.values())
                .filter(e -> StringUtils.equalsIgnoreCase(e.measurementValue, measurementValue))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unsupported type: " + measurementValue));
    }
}

package com.debes.plateplanner.models.enums;

/**
 * @author lesley.debes
 */
public enum ModelStatusEnum {
    SUCCESS(Boolean.TRUE), ERROR(Boolean.FALSE);

    private final Boolean modelStatusValue;
    ModelStatusEnum(Boolean modelStatusValue) {
        this.modelStatusValue = modelStatusValue;
    }

    public Boolean isSuccessful() {
        return this.modelStatusValue;
    }
}

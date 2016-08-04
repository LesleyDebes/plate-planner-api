package com.debes.plateplanner.models;

import com.debes.plateplanner.models.enums.ModelStatusEnum;

/**
 * @author lesley.debes
 */
public class BaseModel {
    private ModelStatusEnum modelStatusEnum = ModelStatusEnum.ERROR;
    private String message;

    public ModelStatusEnum getModelStatusEnum() {
        return modelStatusEnum;
    }

    public void setModelStatusEnum(ModelStatusEnum modelStatusEnum) {
        this.modelStatusEnum = modelStatusEnum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

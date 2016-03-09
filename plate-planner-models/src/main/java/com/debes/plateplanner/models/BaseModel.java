package com.debes.plateplanner.models;

import com.debes.plateplanner.models.enums.ModelStatusEnum;

/**
 * @author lesley.debes
 */
public class BaseModel {
    //TODO: ADD VALIDATION
    public ModelStatusEnum modelStatusEnum = ModelStatusEnum.ERROR;
    public String message;

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

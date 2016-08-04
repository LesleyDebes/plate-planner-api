package com.debes.plateplanner.models.recipe;

import com.debes.plateplanner.models.BaseModel;

import java.util.List;

/**
 * @author lesley.debes
 */
public class MeasurementListModel extends BaseModel {
    private List<MeasurementModel> measurementList;

    public List<MeasurementModel> getMeasurementList() {
        return measurementList;
    }

    public void setMeasurementList(List<MeasurementModel> measurementList) {
        this.measurementList = measurementList;
    }
}

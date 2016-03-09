package com.debes.plateplanner.dao.recipe;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author lesley.debes
 */
@Entity
public class Measurement {
    private String idMeasurement;
    private String measurement;
    private short orderSequence;

    @Id
    @Column(name = "id_measurement", nullable = false, length = 3)
    public String getIdMeasurement() {
        return idMeasurement;
    }

    public void setIdMeasurement(String idMeasurement) {
        this.idMeasurement = idMeasurement;
    }

    @Basic
    @Column(name = "measurement", nullable = false, length = -1)
    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    @Basic
    @Column(name = "order_sequence", nullable = false)
    public short getOrderSequence() {
        return orderSequence;
    }

    public void setOrderSequence(short orderSequence) {
        this.orderSequence = orderSequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Measurement that = (Measurement) o;

        if (orderSequence != that.orderSequence) { return false; }
        if (idMeasurement != null ? !idMeasurement.equals(that.idMeasurement) : that.idMeasurement != null) {
            return false;
        }
        if (measurement != null ? !measurement.equals(that.measurement) : that.measurement != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMeasurement != null ? idMeasurement.hashCode() : 0;
        result = 31 * result + (measurement != null ? measurement.hashCode() : 0);
        result = 31 * result + (int) orderSequence;
        return result;
    }
}

package no.cantara.realestate.mappingtable.rec;

import no.cantara.realestate.mappingtable.tfm.Tfm;

import java.time.Instant;

public class SensorRecObject extends RecObject {


    private String name;
    private String sensorType;
    private String description;
    private String measurementUnit;
    private Instant createdDate;
    private Instant lastUpdatedDate;

    private Tfm tfm;
    public SensorRecObject(String recId) {
        super(recId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }


    public Tfm getTfm() {
        return tfm;
    }

    public void setTfm(Tfm tfm) {
        this.tfm = tfm;
    }
}

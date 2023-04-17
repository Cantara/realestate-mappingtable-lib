package no.cantara.realestate.mappingtable.rec;

import java.time.Instant;

public class SensorRecObject extends RecObject {

    private String realEstate;
    private String building;
    private String floor;
    private String section;
    private String servesRoom;
    private String placementRoom;
    private String climateZone;
    private String electricityZone;
    private String name;
    private String sensorType;
    private String description;
    private String measurementUnit;
    private String recId;
    private Instant createdDate;
    private Instant lastUpdatedDate;
    public SensorRecObject(String recId) {
        super(recId);
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getServesRoom() {
        return servesRoom;
    }

    public void setServesRoom(String servesRoom) {
        this.servesRoom = servesRoom;
    }

    public String getPlacementRoom() {
        return placementRoom;
    }

    public void setPlacementRoom(String placementRoom) {
        this.placementRoom = placementRoom;
    }

    public String getClimateZone() {
        return climateZone;
    }

    public void setClimateZone(String climateZone) {
        this.climateZone = climateZone;
    }

    public String getElectricityZone() {
        return electricityZone;
    }

    public void setElectricityZone(String electricityZone) {
        this.electricityZone = electricityZone;
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

    @Override
    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId;
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

    public String getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(String realEstate) {
        this.realEstate = realEstate;
    }
}

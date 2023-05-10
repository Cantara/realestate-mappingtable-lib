package no.cantara.realestate.mappingtable.rec;

public final class SensorRecBuilder {

    private String realEstate;
    private String tfm;
    private String metasysObjectId;
    private String metasysObjectReference;
    private String metasysNae;
    private String schneiderTrendId;
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

    private SensorRecBuilder() {
    }

    private SensorRecBuilder(String realEstate) {
        this.realEstate = realEstate;
    }

    public static SensorRecBuilder aTfm2Rec(String realEstate) {
        return new SensorRecBuilder(realEstate);
    }

    public SensorRecBuilder withTfm2recId(String recId) {
        this.recId = recId;
        return this;
    }

    public SensorRecBuilder withRealEstate(String realEstate) {
        this.realEstate = realEstate;
        return this;
    }

    public SensorRecBuilder withTfm(String tfm) {
        this.tfm = tfm;
        return this;
    }

    public SensorRecBuilder withmetasysObjectId(String metasysObjectId) {
        this.metasysObjectId = metasysObjectId;
        return this;
    }

    public SensorRecBuilder withMetasysObjectReference(String metasysObjectReference) {
        this.metasysObjectReference = metasysObjectReference;
        return this;
    }

    public SensorRecBuilder withMetasysNae(String metasysNae) {
        this.metasysNae = metasysNae;
        return this;
    }

    public SensorRecBuilder withSchneiderTrendId(String schneiderTrendId) {
        this.schneiderTrendId = schneiderTrendId;
        return this;
    }

    public SensorRecBuilder withBuilding(String building) {
        this.building = building;
        return this;
    }

    public SensorRecBuilder withFloor(String floor) {
        this.floor = floor;
        return this;
    }

    public SensorRecBuilder withSection(String section) {
        this.section = section;
        return this;
    }

    public SensorRecBuilder withServesRoom(String servesRoom) {
        this.servesRoom = servesRoom;
        return this;
    }

    public SensorRecBuilder withPlacementRoom(String placementRoom) {
        this.placementRoom = placementRoom;
        return this;
    }

    public SensorRecBuilder withClimateZone(String climateZone) {
        this.climateZone = climateZone;
        return this;
    }

    public SensorRecBuilder withElectricityZone(String electricityZone) {
        this.electricityZone = electricityZone;
        return this;
    }

    public SensorRecBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SensorRecBuilder withSensorType(String sensorType) {
        this.sensorType = sensorType;
        return this;
    }

    public SensorRecBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public SensorRecBuilder withMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
        return this;
    }

    public SensorRecBuilder withRecId(String recId) {
        this.recId = recId;
        return this;
    }

    public SensorRecObject build() {
        SensorRecObject sensorRecObject = new SensorRecObject(recId);
        sensorRecObject.setRealEstate(this.realEstate);
        sensorRecObject.setBuilding(this.building);
        sensorRecObject.setFloor(this.floor);
        sensorRecObject.setSection(this.section);
        sensorRecObject.setServesRoom(this.servesRoom);
        sensorRecObject.setPlacementRoom(this.placementRoom);
        sensorRecObject.setClimateZone(this.climateZone);
        sensorRecObject.setElectricityZone(this.electricityZone);
        sensorRecObject.setName(this.name);
        sensorRecObject.setSensorType(this.sensorType);
        sensorRecObject.setDescription(this.description);
        sensorRecObject.setMeasurementUnit(this.measurementUnit);
        sensorRecObject.setRecId(this.recId);
        return sensorRecObject;
    }
}

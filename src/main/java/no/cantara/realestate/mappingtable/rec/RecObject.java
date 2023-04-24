package no.cantara.realestate.mappingtable.rec;

public class RecObject {

    private String recId;
    private String realEstate;
    private String building;
    private String floor;

    private String section;
    private String placementRoom;
    private String servesRoom;
    private String  climateZone;
    private String electricityZone;
    private String sensorType;


    public RecObject() {
    }

    public RecObject(String recId) {
        this.recId = recId;
    }

    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId;
    }

    public String getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(String realEstate) {
        this.realEstate = realEstate;
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

    public String getPlacementRoom() {
        return placementRoom;
    }

    public void setPlacementRoom(String placementRoom) {
        this.placementRoom = placementRoom;
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

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    /**
     *
     * @param propertyName
     * @return
     * @throws IllegalArgumentException if propertyName is not a valid property name
     */
    public String getProperty(String propertyName) {
        switch (propertyName) {
            case "recId":
                return recId;
            case "realEstate":
                return realEstate;
            case "building":
                return building;
            case "floor":
                return floor;
            case "placementRoom":
                return placementRoom;
            case "servesRoom":
                return servesRoom;
            case "climateZone":
                return climateZone;
            case "electricityZone":
                return electricityZone;
            case "section":
                return section;
            case "sensorType":
                return sensorType;
            default:
                throw new IllegalArgumentException("Unknown property: " + propertyName);
        }
    }
}

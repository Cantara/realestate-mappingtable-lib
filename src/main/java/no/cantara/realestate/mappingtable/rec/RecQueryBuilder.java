package no.cantara.realestate.mappingtable.rec;

public class RecQueryBuilder {

    private String realEstate;
    private String building;
    private String floor;
    private String placementRoom;
    private String servesRoom;
    private String climateZone;
    private String electricityZone;
    private String sensorType;

    public RecQueryBuilder realEstate(String value) {
        return this;
    }
}

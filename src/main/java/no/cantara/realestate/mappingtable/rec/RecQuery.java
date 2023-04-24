package no.cantara.realestate.mappingtable.rec;

import no.cantara.realestate.mappingtable.MappedSensorId;

import java.util.function.Predicate;

public class RecQuery {

    private String realEstate;
    private String building;
    private String floor;
    private String placementRoom;
    private String servesRoom;
    private String climateZone;
    private String electricityZone;
    private String sensorType;

    private Predicate<MappedSensorId> predicate;


    public Predicate<MappedSensorId> getPredicate() {
        return predicate;
    }
}

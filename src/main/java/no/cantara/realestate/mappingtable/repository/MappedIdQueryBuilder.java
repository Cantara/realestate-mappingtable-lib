package no.cantara.realestate.mappingtable.repository;

import no.cantara.realestate.mappingtable.MappedSensorId;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MappedIdQueryBuilder {

    List<Predicate<MappedSensorId>> predicates = new ArrayList<>();

    private String realEstate;
    private String building;
    private String floor;
    private String placementRoom;
    private String servesRoom;
    private String climateZone;
    private String electricityZone;

    private String sensorType;

    public MappedIdQueryBuilder realEstate(String value) {
        this.realEstate = value;
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getRealEstate().equals(value));
        return this;
    }
    public MappedIdQueryBuilder building(String value) {
        this.building = value;
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getBuilding().equals(value));
        return this;
    }

    public MappedIdQueryBuilder floor(String value) {
        this.floor = value;
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getFloor().equals(value));
        return this;
    }
    public MappedIdQueryBuilder placementRoom(String value) {
        this.placementRoom = value;
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getPlacementRoom().equals(value));
        return this;
    }
    public MappedIdQueryBuilder servesRoom(String value) {
        this.servesRoom = value;
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getServesRoom().equals(value));
        return this;
    }
    public MappedIdQueryBuilder climateZone(String value) {
        this.climateZone = value;
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getClimateZone().equals(value));
        return this;
    }
    public MappedIdQueryBuilder electricityZone(String value) {
        this.electricityZone = value;
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getElectricityZone().equals(value));
        return this;
    }

    public MappedIdQueryBuilder sensorType(String value) {
        this.sensorType = value;
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getSensorType().equals(value));
        return this;
    }

    public MappedIdQuery build() {
        Predicate<MappedSensorId> predicate = predicates.stream().reduce(Predicate::and).orElse(mappedSensorId -> true);
        MappedIdQuery query = new MappedIdQuery(predicate);
        query.setRealEstate(realEstate);
        query.setBuilding(building);
        query.setFloor(floor);
        query.setPlacementRoom(placementRoom);
        query.setServesRoom(servesRoom);
        query.setClimateZone(climateZone);
        query.setElectricityZone(electricityZone);
        query.setSensorType(sensorType);

        return query;
    }

    public MappedIdQueryBuilder sensorIdClass(Class sensorIdClass) {
        if (sensorIdClass != null) {
            predicates.add(mappedSensorId -> mappedSensorId.getSensorId() != null);
            predicates.add(mappedSensorId -> mappedSensorId.getSensorId().getClass().equals(sensorIdClass));
        }
        return this;
    }

    public MappedIdQueryBuilder recPropertyIsNull(String propertyName) {
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getProperty(propertyName) == null || mappedSensorId.getRec().getProperty(propertyName).isEmpty());
        return this;
    }

    public MappedIdQueryBuilder missingSensorId() {
        predicates.add(mappedSensorId -> mappedSensorId.getSensorId() == null);
        return this;
    }
}

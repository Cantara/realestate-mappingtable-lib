package no.cantara.realestate.mappingtable.repository;

import no.cantara.realestate.mappingtable.MappedSensorId;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MappedIdQueryBuilder {

    List<Predicate<MappedSensorId>> predicates = new ArrayList<>();

    public MappedIdQueryBuilder realEstate(String value) {
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getRealEstate().equals(value));
        return this;
    }
    public MappedIdQueryBuilder building(String value) {
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getBuilding().equals(value));
        return this;
    }

    public MappedIdQueryBuilder floor(String value) {
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getFloor().equals(value));
        return this;
    }
    public MappedIdQueryBuilder placementRoom(String value) {
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getPlacementRoom().equals(value));
        return this;
    }
    public MappedIdQueryBuilder servesRoom(String value) {
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getServesRoom().equals(value));
        return this;
    }
    public MappedIdQueryBuilder climateZone(String value) {
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getClimateZone().equals(value));
        return this;
    }
    public MappedIdQueryBuilder electricityZone(String value) {
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getElectricityZone().equals(value));
        return this;
    }

    public MappedIdQueryBuilder sensorType(String value) {
        predicates.add(mappedSensorId -> mappedSensorId.getRec() != null);
        predicates.add(mappedSensorId -> mappedSensorId.getRec().getSensorType().equals(value));
        return this;
    }

    public MappedIdQuery build() {
        Predicate<MappedSensorId> predicate = predicates.stream().reduce(Predicate::and).orElse(mappedSensorId -> true);
        MappedIdQuery query = new MappedIdQuery(predicate);
        return query;
    }

}

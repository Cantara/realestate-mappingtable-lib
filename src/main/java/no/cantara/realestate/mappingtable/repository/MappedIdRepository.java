package no.cantara.realestate.mappingtable.repository;

import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.MappingKey;

import java.util.List;

public interface MappedIdRepository {

    void add(MappedSensorId sensorId);
    List<MappedSensorId> find(MappingKey mappingKey);

    long size();
}

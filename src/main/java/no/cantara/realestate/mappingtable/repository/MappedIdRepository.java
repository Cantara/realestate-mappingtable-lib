package no.cantara.realestate.mappingtable.repository;

import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.UniqueKey;
import no.cantara.realestate.mappingtable.rec.RecObject;

import java.util.List;

public interface MappedIdRepository {

    void add(MappedSensorId sensorId);
    List<MappedSensorId> find(UniqueKey mappingKey);

    List<MappedSensorId> find(MappedIdQuery mappedIdQuery);

    long updateRec(RecObject recObject);

    long size();
}

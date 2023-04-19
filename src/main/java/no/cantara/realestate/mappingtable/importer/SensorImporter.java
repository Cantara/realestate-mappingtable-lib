package no.cantara.realestate.mappingtable.importer;

import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.SensorId;

import java.util.List;

public interface SensorImporter {

    List<SensorId> importSensors(String sourceType);
    List<MappedSensorId> importMappedId(String sourceType);
}

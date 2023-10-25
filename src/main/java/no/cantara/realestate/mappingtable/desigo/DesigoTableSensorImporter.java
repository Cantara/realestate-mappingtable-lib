package no.cantara.realestate.mappingtable.desigo;

import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.SensorId;
import no.cantara.realestate.mappingtable.importer.SensorImporter;
import no.cantara.realestate.mappingtable.rec.SensorRecObject;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static no.cantara.realestate.mappingtable.importer.SensorMapper.importSensorRecObject;
import static org.slf4j.LoggerFactory.getLogger;

public class DesigoTableSensorImporter implements SensorImporter {
    private static final Logger log = getLogger(DesigoTableSensorImporter.class);

    private final List<Map<String, String>> tableRows;

    public DesigoTableSensorImporter(List<Map<String, String>> tableRows) {
        this.tableRows = tableRows;
    }

    @Override
    public List<SensorId> importSensors(String sourceType) {
        List<SensorId> sensorIds = new ArrayList<>();
        for (Map<String, String> row : tableRows) {
            DesigoSensorId sensorId = new DesigoSensorId( row.get("DesigoId"), row.get("DesigoPropertyId"));
            String desigoTrendId = row.get("DesigoTrendId");
            if (desigoTrendId != null && !desigoTrendId.isEmpty()) {
                sensorId.setTrendId(desigoTrendId);
            }
            sensorIds.add(sensorId);
        }
        return sensorIds;

    }

    @Override
    public List<MappedSensorId> importMappedId(String sourceType) {

        List<MappedSensorId> mappedSensorIds = new ArrayList<>();
        for (Map<String, String> row : tableRows) {
            List<String> columnNames = new ArrayList<>(row.keySet());
            SensorId sensorId = new DesigoSensorId(row.get("DesigoId"), row.get("DesigoPropertyId"));
            SensorRecObject sensorRecObject = importSensorRecObject(columnNames, row);
            MappedSensorId mappedSensorId = new MappedSensorId(sensorId, sensorRecObject);
            mappedSensorIds.add(mappedSensorId);
        }
        return mappedSensorIds;
    }
}

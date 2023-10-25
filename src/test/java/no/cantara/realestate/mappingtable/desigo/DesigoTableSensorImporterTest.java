package no.cantara.realestate.mappingtable.desigo;

import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.SensorId;
import no.cantara.realestate.mappingtable.importer.SensorImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DesigoTableSensorImporterTest {

    private SensorImporter desigoTableSensorImporter;

    @BeforeEach
    void setUp() {
        List<Map<String, String>> rows = new ArrayList<>();
        rows.add(createSensorConfigRow("Rec1", "DesigoId1", "DesigoPropertyId1", "TrendId1","CO2", "PPM"));
        desigoTableSensorImporter = new DesigoTableSensorImporter(rows);

    }

    @Test
    void importSensors() {
        List<SensorId> sensorIds = desigoTableSensorImporter.importSensors("Desigo");
        assertNotNull(sensorIds);
        assertEquals(1, sensorIds.size());
        assertEquals(DesigoSensorId.class, sensorIds.get(0).getClass());
        DesigoSensorId sensorId = (DesigoSensorId) sensorIds.get(0);
        assertEquals("DesigoId1", sensorId.getDesigoId());
        assertEquals("DesigoPropertyId1", sensorId.getDesigoPropertyId());
        assertEquals("TrendId1", sensorId.getTrendId());
    }

    @Test
    void importMappedId() {
        List<MappedSensorId> mappedSensorIds = desigoTableSensorImporter.importMappedId("Desigo");
        assertNotNull(mappedSensorIds);
        assertEquals(1, mappedSensorIds.size());
        assertEquals(MappedSensorId.class, mappedSensorIds.get(0).getClass());
        MappedSensorId mappedSensorId = mappedSensorIds.get(0);
        assertEquals("CO2", mappedSensorId.getRec().getSensorType());
        assertEquals("ServesRoom1", mappedSensorId.getRec().getServesRoom());
    }

    private Map<String, String> createSensorConfigRow(String digitalTwinSensorId, String desigoId, String desigoPropertyId, String trendId, String sensorType, String measurementUnit) {
        Map<String, String> row = new HashMap<>();
        row.put("DigitalTwinSensorId", digitalTwinSensorId);
        row.put("DesigoId", desigoId);
        row.put("DesigoPropertyId", desigoPropertyId);
        row.put("DesigoTrendId", trendId);
        row.put("SensorType", sensorType);
        row.put("Tfm", "todoTfm");
        row.put("Name", "todoName");
        row.put("Description", "Description");
        row.put("RealEstate", "RealEstate1");
        row.put("Building", "Building1");
        row.put("Section", "Section1");
        row.put("Floor", "Floor1");
        row.put("ServesRoom", "ServesRoom1");
        row.put("PlacementRoom", "PlacementRoom");
        row.put("Interval", "5m");
        return row;
    }
//    id, DigitalTwinSensorId,Tfm,MetasysObjectReference,MetasysObjectId,Name,Description,RealEstate,Building,Section,Floor,ServesRoom,PlacementRoom,SensorType,MeasurementUnit,Interval
}
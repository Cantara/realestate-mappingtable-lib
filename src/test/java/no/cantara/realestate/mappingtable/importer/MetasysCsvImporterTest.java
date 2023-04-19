package no.cantara.realestate.mappingtable.importer;

import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.SensorId;
import no.cantara.realestate.mappingtable.metasys.MetasysCsvSensorImporter;
import no.cantara.realestate.mappingtable.rec.SensorRecObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MetasysCsvImporterTest {

    private CsvSensorImporter csvImporter;

    @BeforeEach
    void setUp() {
        File importDirectory = new File("src/test/resources");
        csvImporter = new MetasysCsvSensorImporter(importDirectory);
    }

    @Test
    void importSensors() {
        List<SensorId> metasysSensors = csvImporter.importSensors("Metasys");
        assertTrue(metasysSensors != null);
        assertEquals(3, metasysSensors.size());
    }

    @Test
    void importMappedId() {
        List<MappedSensorId> mappedSensorIds = csvImporter.importMappedId("Metasys");
        assertTrue(mappedSensorIds != null);
        assertEquals(3, mappedSensorIds.size());
        assertEquals("", mappedSensorIds.get(0).getRec().getRecId());
        assertEquals("aaa-bbb-ccc", mappedSensorIds.get(2).getRec().getRecId());
        assertEquals("TFM-RY02101",mappedSensorIds.get(0).getRec().getTfm().getTfm());
        SensorRecObject mappedRec = mappedSensorIds.get(1).getRec();
        assertEquals("433.012-OE101-Energy", mappedRec.getTfm().getTfm());
        assertEquals("RE1",mappedRec.getRealEstate());
        assertEquals("Building1",mappedRec.getBuilding());
        assertEquals("Floor1",mappedRec.getFloor());
        assertEquals("ventilasjon",mappedRec.getElectricityZone());
        assertEquals("Energy", mappedRec.getSensorType());
        assertEquals("kwh", mappedRec.getMeasurementUnit());
    }

    @Test
    void findEligibleFiles() {
        assertEquals(3, csvImporter.findEligibleFiles("Metasys").size());
    }
}
package no.cantara.realestate.mappingtable.importer;

import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.SensorId;
import no.cantara.realestate.mappingtable.metasys.MetasysCsvSensorImporter;
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
        assertEquals(2, metasysSensors.size());
    }

    @Test
    void importMappedId() {
        List<MappedSensorId> mappedSensorIds = csvImporter.importMappedId("Metasys");
        assertTrue(mappedSensorIds != null);
        assertEquals(2, mappedSensorIds.size());
        assertEquals("", mappedSensorIds.get(0).getRec().getRecId());
        assertEquals("aaa-bbb-ccc", mappedSensorIds.get(1).getRec().getRecId());

    }

    @Test
    void findEligibleFiles() {
        assertEquals(2, csvImporter.findEligibleFiles("Metasys").size());
    }
}
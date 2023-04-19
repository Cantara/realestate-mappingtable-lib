package no.cantara.realestate.mappingtable.importer;

import no.cantara.realestate.mappingtable.SensorId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvImporterTest {

    private CsvSensorImporter csvImporter;

    @BeforeEach
    void setUp() {
        File importDirectory = new File("src/test/resources");
        csvImporter = new CsvSensorImporter(importDirectory);
    }

    @Test
    void importSensors() {
        List<SensorId> metasysSensors = csvImporter.importSensors("Metasys");
        assertTrue(metasysSensors != null);
        assertEquals(5, metasysSensors.size());
    }

    @Test
    void findEligibleFiles() {
        assertEquals(2, csvImporter.findEligibleFiles("Metasys").size());
    }
}
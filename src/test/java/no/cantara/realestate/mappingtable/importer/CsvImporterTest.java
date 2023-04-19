package no.cantara.realestate.mappingtable.importer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvImporterTest {

    private CsvImporter csvImporter;

    @BeforeEach
    void setUp() {
        File importDirectory = new File("src/test/resources");
        csvImporter = new CsvImporter(importDirectory);
    }

    @Test
    void importSensors() {
    }

    @Test
    void findEligibleFiles() {
        assertEquals(2, csvImporter.findEligibleFiles("Metasys").size());
    }
}
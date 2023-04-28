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
    void importMappedIdVerifyTfm() {
        List<MappedSensorId> mappedSensorIds = csvImporter.importMappedId("Metasys");
        assertTrue(mappedSensorIds != null);
        assertEquals(3, mappedSensorIds.size());
        for (MappedSensorId mappedSensorId : mappedSensorIds) {
            if (mappedSensorId.getRec().getRecId() == null) {
                assertEquals("433.012-OE101-Energy", mappedSensorId.getRec().getTfm().getTfm());
            }
        }
    }

    @Test
    void importMappedIdVerifyRecObject() {
        List<MappedSensorId> mappedSensorIds = csvImporter.importMappedId("Metasys");
        assertTrue(mappedSensorIds != null);
        assertEquals(3, mappedSensorIds.size());
        for (MappedSensorId mappedSensorId : mappedSensorIds) {
            if (mappedSensorId.getRec().getRecId() == null) {
                SensorRecObject mappedRec = mappedSensorId.getRec();
                assertEquals("433.012-OE101-Energy", mappedRec.getTfm().getTfm());
                assertEquals("RE1", mappedRec.getRealEstate());
                assertEquals("Building1", mappedRec.getBuilding());
                assertEquals("Floor1", mappedRec.getFloor());
                assertEquals("ventilasjon", mappedRec.getElectricityZone());
                assertEquals("Energy", mappedRec.getSensorType());
                assertEquals("kwh", mappedRec.getMeasurementUnit());
            }
        }
    }
    @Test
    void importMappedIdVerifyRecId() {
        //Test that recId is defined with value, is blank or is null in the csv file
        List<MappedSensorId> mappedSensorIds = csvImporter.importMappedId("Metasys");
        assertTrue(mappedSensorIds != null);
        assertEquals(3, mappedSensorIds.size());
        for (MappedSensorId mappedSensorId : mappedSensorIds) {
            String recId = mappedSensorId.getRec().getRecId();
            assertTrue(recId == null || recId.equals("") || recId.equals("aaa-bbb-ccc"));
        }
    }

    @Test
    void findEligibleFiles() {
        assertEquals(3, csvImporter.findEligibleFiles("Metasys").size());
    }
}
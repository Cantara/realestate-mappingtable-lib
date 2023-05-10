package no.cantara.realestate.mappingtable.importer;

import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.SensorId;
import no.cantara.realestate.mappingtable.metasys.MetasysCsvSensorImporter;
import no.cantara.realestate.mappingtable.metasys.MetasysSensorId;
import no.cantara.realestate.mappingtable.rec.SensorRecObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MetasysCsvImporterTest {

    private CsvSensorImporter csvImporter;
    private File importDirectory;

    @BeforeEach
    void setUp() {
        importDirectory = new File("src/test/resources");
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
    void importMappedIdFromFile() {
        List<MappedSensorId> mappedSensorIds = csvImporter.importMappedIdFromFile(Paths.get("src/test/resources/MetasysTfmRec.csv"));
        assertTrue(mappedSensorIds != null);
        assertEquals(1, mappedSensorIds.size());

        MetasysSensorId sensorId = (MetasysSensorId) mappedSensorIds.get(0).getSensorId();
        assertEquals("208540b1-ab8a-566a-8a41-8b4cee515baf", sensorId.getMetasysObjectId());
        assertEquals("METASYS1:RE1-NAE7/BACnet IP1.Modbus 433U11.Analog Inputs.AI-93", sensorId.getMetasysObjectReference());

    }
    //importMappedIdFromFile

    void importVerifyMetasysProperties() {
        List<MappedSensorId> mappedSensorIds = csvImporter.importMappedId("Metasys");
        assertTrue(mappedSensorIds != null);
        assertEquals(3, mappedSensorIds.size());
        boolean metasysObjectIdFound = false;
        boolean metasysObjectReferenceFound = false;
        for (MappedSensorId mappedSensorId : mappedSensorIds) {
            MetasysSensorId sensorId = (MetasysSensorId) mappedSensorId.getSensorId();
            String metasysObjectId = sensorId.getMetasysObjectId();
            assertTrue(metasysObjectId == null && !metasysObjectId.isEmpty());
            if (metasysObjectId.equals("208540b1-ab8a-566a-8a41-8b4cee515baf")){
                metasysObjectIdFound = true;
            }
            assertTrue(sensorId.getMetasysObjectReference() != null && !sensorId.getMetasysObjectReference().isEmpty());
            if (sensorId.getMetasysObjectReference().equals("METASYS1:RE1-NAE7/BACnet IP1.Modbus 433U11.Analog Inputs.AI-93")){
                metasysObjectReferenceFound = true;
            }
        }
        assertTrue( metasysObjectIdFound,"MetasysObjectId not found: 208540b1-ab8a-566a-8a41-8b4cee515baf");
        assertTrue(metasysObjectReferenceFound,"MetasysObjectReference not found: METASYS1:RE1-NAE7/BACnet IP1.Modbus 433U11.Analog Inputs.AI-93");
    }

    @Test
    void findEligibleFiles() {
        assertEquals(3, csvImporter.findEligibleFiles("Metasys").size());
    }
}
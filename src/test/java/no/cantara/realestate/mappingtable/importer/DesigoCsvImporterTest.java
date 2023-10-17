package no.cantara.realestate.mappingtable.importer;

import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.SensorId;
import no.cantara.realestate.mappingtable.desigo.DesigoCsvSensorImporter;
import no.cantara.realestate.mappingtable.desigo.DesigoSensorId;
import no.cantara.realestate.mappingtable.rec.RecObject;
import no.cantara.realestate.mappingtable.rec.SensorRecObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DesigoCsvImporterTest {

    private CsvSensorImporter csvImporter;
    private File importDirectory;

    @BeforeEach
    void setUp() {
        importDirectory = new File("src/test/resources");
        csvImporter = new DesigoCsvSensorImporter(importDirectory);
    }

    @Test
    void importSensors() {
        List<SensorId> desigoSensors = csvImporter.importSensors("Desigo");
        assertTrue(desigoSensors != null);
        assertEquals(3, desigoSensors.size());
    }

    @Test
    void importMappedIdVerifyTfm() {
        List<MappedSensorId> mappedSensorIds = csvImporter.importMappedId("Desigo");
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
        List<MappedSensorId> mappedSensorIds = csvImporter.importMappedId("Desigo");
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
        List<MappedSensorId> mappedSensorIds = csvImporter.importMappedId("Desigo");
        assertTrue(mappedSensorIds != null);
        assertEquals(3, mappedSensorIds.size());
        for (MappedSensorId mappedSensorId : mappedSensorIds) {
            String recId = mappedSensorId.getRec().getRecId();
            assertTrue(recId == null || recId.equals("") || recId.equals("aaa-bbb-ccc"));
        }
    }

    @Test
    void importMappedIdFromFile() {
        List<MappedSensorId> mappedSensorIds = csvImporter.importMappedIdFromFile(Paths.get("src/test/resources/DesigoTfmRec.csv"));
        assertTrue(mappedSensorIds != null);
        assertEquals(1, mappedSensorIds.size());
        assertTrue(mappedSensorIds.size() > 0);
        RecObject recObject = mappedSensorIds.get(0).getRec();
        assertEquals("Fløy1", recObject.getSection());
        assertEquals("123", recObject.getServesRoom());
        assertEquals("0123", recObject.getPlacementRoom());

        DesigoSensorId sensorId = (DesigoSensorId) mappedSensorIds.get(0).getSensorId();
        assertEquals("System1:GmsDevice_2_1016052_121634835", sensorId.getDesigoId());
        assertEquals("RAQual_Present_Value", sensorId.getDesigoPropertyId());


    }
    //importMappedIdFromFile

    void importVerifyDesigoProperties() {
        List<MappedSensorId> mappedSensorIds = csvImporter.importMappedId("Desigo");
        assertTrue(mappedSensorIds != null);
        assertEquals(3, mappedSensorIds.size());
        boolean desigoObjectIdFound = false;
        boolean desigoObjectReferenceFound = false;
        for (MappedSensorId mappedSensorId : mappedSensorIds) {
            DesigoSensorId sensorId = (DesigoSensorId) mappedSensorId.getSensorId();
            String desigoId = sensorId.getDesigoId();
            assertTrue(desigoId == null && !desigoId.isEmpty());
            if (desigoId.equals("208540b1-ab8a-566a-8a41-8b4cee515baf")){
                desigoObjectIdFound = true;
            }
            assertTrue(sensorId.getDesigoPropertyId() != null && !sensorId.getDesigoPropertyId().isEmpty());
            if (sensorId.getDesigoId().equals("DESIGO1:RE1-NAE7/BACnet IP1.Modbus 433U11.Analog Inputs.AI-93")){
                desigoObjectReferenceFound = true;
            }
        }
        assertTrue( desigoObjectIdFound,"DesigoObjectId not found: 208540b1-ab8a-566a-8a41-8b4cee515baf");
        assertTrue(desigoObjectReferenceFound,"DesigoObjectReference not found: DESIGO1:RE1-NAE7/BACnet IP1.Modbus 433U11.Analog Inputs.AI-93");
    }

    @Test
    void findEligibleFiles() {
        assertEquals(3, csvImporter.findEligibleFiles("Desigo").size());
    }
}
package no.cantara.realestate.mappingtable.desigo;

import no.cantara.realestate.mappingtable.csv.CsvCollection;
import no.cantara.realestate.mappingtable.csv.CsvReader;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ReadDesigoCsvTest {

    @Test
    void readBasic() {
        CsvCollection collection = CsvReader.parse("src/test/resources/DesigoBasic.csv");
        assertNotNull(collection);
        assertTrue(collection.getColumnNames().contains("DesigoId"));
        assertTrue(collection.getColumnNames().contains("DesigoPropertyId"));
        assertTrue(collection.getColumnNames().contains("RecId") || collection.getColumnNames().contains("DigitalTwinSensorId"));
        Map record = collection.getRecords().get(0);
        assertEquals("System1:GmsDevice_2_1016052_121634835", record.get("DesigoId"));
        assertEquals("RAQual_Present_Value", record.get("DesigoPropertyId"));
        assertEquals("aaa-bbb-ccc", record.get("RecId"));
    }

    @Test
    void readWithTverrfagligMerkesystem() {
        //RecId is deliberately missing from this test file.
        CsvCollection collection = CsvReader.parse("src/test/resources/DesigoTfm.csv");
        assertNotNull(collection);
        assertTrue(collection.getColumnNames().contains("Tfm"));
        assertTrue(collection.getColumnNames().contains("RecId"));
        Map record = collection.getRecords().get(0);
        assertEquals("TFM-RY02101", record.get("Tfm"));
        assertEquals("", record.get("RecId"));
    }
}

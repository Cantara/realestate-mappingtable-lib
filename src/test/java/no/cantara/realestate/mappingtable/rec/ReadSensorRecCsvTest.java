package no.cantara.realestate.mappingtable.rec;

import no.cantara.realestate.mappingtable.csv.CsvCollection;
import no.cantara.realestate.mappingtable.csv.CsvReader;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ReadSensorRecCsvTest {

    @Test
    void readBasic() {
        CsvCollection collection = CsvReader.parse("src/test/resources/RecSensor.csv");
        assertNotNull(collection);
        assertTrue(collection.getColumnNames().contains("RecId"));
        assertTrue(collection.getColumnNames().contains("RealEstate"));
        assertTrue(collection.getColumnNames().contains("Section"));
        Map record = collection.getRecords().get(0);
        assertEquals("aaa-bbb-ccc", record.get("RecId"));
        assertEquals("re16", record.get("RealEstate"));
        assertEquals("",record.get("Section"));
    }

    @Test
    void readTfm() {
        CsvCollection collection = CsvReader.parse("src/test/resources/RecSensorTfm.csv");
        assertNotNull(collection);
        assertTrue(collection.getColumnNames().contains("RecId"));
        assertTrue(collection.getColumnNames().contains("Tfm"));
        Map record = collection.getRecords().get(0);
        assertEquals("aaa-bbb-ccc", record.get("RecId"));
        assertEquals("TFM-RY02101", record.get("Tfm"));
    }
}

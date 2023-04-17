package no.cantara.realestate.mappingtable.metasys;

import no.cantara.realestate.mappingtable.csv.CsvCollection;
import no.cantara.realestate.mappingtable.csv.CsvReader;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ReadMetasysCsvTest {

    @Test
    void readBasic() {
        CsvCollection collection = CsvReader.parse("src/test/resources/MetasysBasic.csv");
        assertNotNull(collection);
        assertTrue(collection.getColumnNames().contains("MetasysObjectId"));
        assertTrue(collection.getColumnNames().contains("MetasysObjectReference"));
        assertTrue(collection.getColumnNames().contains("RecId"));
        Map record = collection.getRecords().get(0);
        assertEquals("server1:re16-NAE7/BACnet IP1.Modbus 433U11.Analog Inputs.AI-93", record.get("MetasysObjectReference"));
        assertEquals("208540b1-ab8a-566a-8a41-8b4cee51abcd", record.get("MetasysObjectId"));
        assertEquals("aaa-bbb-ccc", record.get("RecId"));
    }
}

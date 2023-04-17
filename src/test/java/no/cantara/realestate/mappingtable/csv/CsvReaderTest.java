package no.cantara.realestate.mappingtable.csv;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CsvReaderTest {

    @Test
    void readMetasysBasic() {
        Map<String, String> anything = CsvReader.findObjectIds("src/test/resources/MetasysBasic.csv");
        assertNotNull(anything);
    }

    @Test
    void readGeneric() {
        CsvCollection collection = CsvReader.parse("src/test/resources/MetasysBasic.csv");
        assertNotNull(collection);
    }
}
package no.cantara.realestate.mappingtable.csv;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CsvReaderTest {

    @Test
    void readMetasysBasic() {
//        File inputFile = new File("import-config/metasys_objectid.csv");
        Map<String, String> anything = CsvReader.findObjectIds("test/resources/MetasysBasic.csv");
        assertNotNull(anything);
    }
}
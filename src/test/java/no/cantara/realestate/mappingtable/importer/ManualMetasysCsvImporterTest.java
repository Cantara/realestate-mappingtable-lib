package no.cantara.realestate.mappingtable.importer;

import no.cantara.realestate.mappingtable.SensorId;
import no.cantara.realestate.mappingtable.metasys.MetasysCsvSensorImporter;
import org.slf4j.Logger;

import java.io.File;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class ManualMetasysCsvImporterTest {
    private static final Logger log = getLogger(ManualMetasysCsvImporterTest.class);

    public static void main(String[] args) {
        File importDirectory = new File("import-data");
        CsvSensorImporter csvImporter = new MetasysCsvSensorImporter(importDirectory);
        List<SensorId> metasysSensors = csvImporter.importSensors("metasys");
        log.debug("metasysSensors: {}", metasysSensors.size());
    }
}

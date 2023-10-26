package no.cantara.realestate.mappingtable.desigo;

import no.cantara.config.ApplicationProperties;
import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.SensorId;
import no.cantara.realestate.mappingtable.csv.CsvCollection;
import no.cantara.realestate.mappingtable.csv.CsvReader;
import no.cantara.realestate.mappingtable.importer.CsvSensorImporter;
import no.cantara.realestate.mappingtable.rec.SensorRecObject;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static no.cantara.realestate.mappingtable.Main.getConfigValue;
import static org.slf4j.LoggerFactory.getLogger;

public class DesigoCsvSensorImporter extends CsvSensorImporter {
    private static final Logger log = getLogger(DesigoCsvSensorImporter.class);

    public DesigoCsvSensorImporter(File importDirectory) {
        super(importDirectory);
    }

    public List<SensorId> importSensorsFromFile(Path filepath) {
        List<SensorId> sensorIds = new ArrayList<>();
        CsvCollection collection = CsvReader.parse(filepath.toString());
        log.debug("ColumnNames: {}",collection.getColumnNames());
        for (Map<String, String> record : collection.getRecords()) {
            DesigoSensorId sensorId = new DesigoSensorId( record.get("DesigoId"),record.get("DesigoPropertyId"));
            if (record.containsKey("DesigoTrendId")) {
                sensorId.setTrendId(record.get("DesigoTrendId"));
            }
            sensorIds.add(sensorId);
        }
        return sensorIds;
    }

    @Override
    public List<MappedSensorId> importMappedIdFromFile(Path filepath) {
        List<MappedSensorId> mappedSensorIds = new ArrayList<>();
        CsvCollection collection = CsvReader.parse(filepath.toString());
        List<String> columnNames = collection.getColumnNames();
        log.debug("ColumnNames: {}", columnNames);
        for (Map<String, String> record : collection.getRecords()) {

            DesigoSensorId sensorId = new DesigoSensorId( record.get("DesigoId"),record.get("DesigoPropertyId"));
            if (record.containsKey("DesigoTrendId")) {
                sensorId.setTrendId(record.get("DesigoTrendId"));
            }
            SensorRecObject sensorRecObject = importSensorRecObject(columnNames, record);
            MappedSensorId mappedSensorId = new MappedSensorId(sensorId, sensorRecObject);
            mappedSensorIds.add(mappedSensorId);
        }
        return mappedSensorIds;
    }

    public static void main(String[] args) {
        ApplicationProperties.builder().defaults().buildAndSetStaticSingleton();
        String importDirectoryPath = getConfigValue("CSV_IMPORT_DIRECTORY");
        File importDirectory = new File(importDirectoryPath);
        DesigoCsvSensorImporter csvImporter = new DesigoCsvSensorImporter(importDirectory);
        List<Path> convertableFiles = csvImporter.findEligibleFiles("desigo");
        for (Path convertableFile : convertableFiles) {
            log.debug("Import from csv 2: {}", convertableFile);
            /*
            JsonArray csvAsJson = convertFile(convertableFile);
            String convertableFilePath = convertableFile.toString();
            String writeToFile = convertableFilePath.replace("csv", "json");
            writeJson2File(csvAsJson, writeToFile);

             */
        }
        log.info("Imported {} files to {}", convertableFiles.size(), importDirectoryPath);
    }
}

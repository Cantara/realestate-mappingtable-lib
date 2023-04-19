package no.cantara.realestate.mappingtable.importer;

import no.cantara.realestate.mappingtable.SensorId;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class CsvSensorImporter implements SensorImporter {
    private static final Logger log = getLogger(CsvSensorImporter.class);
    private final File importDirectory;

    public CsvSensorImporter(File importDirectory) {
        if (canRead(importDirectory)) {
            this.importDirectory = importDirectory;
        } else {
            throw new IllegalArgumentException("Can not read from: " + importDirectory);
        }
    }

    @Override
    public List<SensorId> importSensors(String sourceType) {
        List<SensorId> sensorIds = new ArrayList<>();
        List<Path> eligibleFiles = findEligibleFiles(sourceType);
        if (eligibleFiles != null) {
            for (Path eligibleFile : eligibleFiles) {
                List<SensorId> importedSensors = importSensorsFromFile(eligibleFile);
                if (importedSensors != null) {
                    sensorIds.addAll(importedSensors);
                }
            }
        }
        return sensorIds;
    }

    public abstract List<SensorId> importSensorsFromFile(Path filepath); /* {
        List<SensorId> sensorIds = new ArrayList<>();
        CsvCollection collection = CsvReader.parse(filepath.toString());
        for (Map<String, String> record : collection.getRecords()) {
            log.debug("ColumnNames: {}",collection.getColumnNames());
            //MetasysObjectReference,MetasysObjectId,RecId
            //tfm,metasysObjectReference,metasysDbId,name,description,realEstate,interval,building,floor,electricityZone,sensorType,measurementUnit
            SensorId sensorId = new MetasysSensorId(record.get("metasysObjectReference"), record.get("MetasysObjectId"));
            sensorIds.add(sensorId);
        }
        return sensorIds;
    }
    */


    boolean canRead(File importDirectory) {
        return importDirectory != null && importDirectory.isDirectory() && importDirectory.canRead();
    }

    protected List<Path> findEligibleFiles(String sourceType) {
        List<Path> result;

        try (Stream<Path> walk = Files.walk(Paths.get(importDirectory.getAbsolutePath()))) {

            result = walk.filter(Files::isRegularFile)
                    .filter(path -> fileIsEligible(sourceType, path))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.debug("Failed to find files in {}", importDirectory);
            result = new ArrayList<>();
        }
        return result;
    }

    protected boolean fileIsEligible(String filePrefix, Path path) {
        boolean isEligible = false;
        if (path != null) {
            String[] pathElements = path.toString().split(File.separator);
            String lastElement = pathElements[pathElements.length - 1];
            if (lastElement.startsWith(filePrefix) && lastElement.endsWith("csv")) {
                isEligible = true;
            }
        }
        return isEligible;
    }


}

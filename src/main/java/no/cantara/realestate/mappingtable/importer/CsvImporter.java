package no.cantara.realestate.mappingtable.importer;

import no.cantara.config.ApplicationProperties;
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

import static no.cantara.realestate.mappingtable.Main.getConfigValue;
import static org.slf4j.LoggerFactory.getLogger;

public class CsvImporter implements SensorImporter {
    private static final Logger log = getLogger(CsvImporter.class);
    private final File importDirectory;

    public CsvImporter(File importDirectory) {
        if (canRead(importDirectory)) {
            this.importDirectory = importDirectory;
        } else {
            throw new IllegalArgumentException("Can not read from: " + importDirectory);
        }
    }

    @Override
    public List<SensorId> importSensors(String sourceType) {
        return null;
    }

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

    public static void main(String[] args) {
        ApplicationProperties.builder().defaults().buildAndSetStaticSingleton();
        String importDirectoryPath = getConfigValue("CSV_IMPORT_DIRECTORY");
        File importDirectory = new File(importDirectoryPath);
        CsvImporter csvImporter = new CsvImporter(importDirectory);
        List<Path> convertableFiles = csvImporter.findEligibleFiles("metasys");
        for (Path convertableFile : convertableFiles) {
            log.debug("Convert from csv 2 json: {}", convertableFile);
            /*
            JsonArray csvAsJson = convertFile(convertableFile);
            String convertableFilePath = convertableFile.toString();
            String writeToFile = convertableFilePath.replace("csv", "json");
            writeJson2File(csvAsJson, writeToFile);

             */
        }
        log.info("Wrote {} files to {}", convertableFiles.size(), importDirectoryPath);
    }
}

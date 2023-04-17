package no.cantara.realestate.mappingtable.csv;

import no.cantara.realestate.mappingtable.MappingTableException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class CsvReader {
    private static final Logger log = getLogger(CsvReader.class);

    public static CsvCollection parse(String csvFilePath) {
        List<String> columnNames = null;
        List<Map<String, String>> records = new ArrayList<>();
        CsvCollection collection = null;
        try (

                Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            columnNames = csvParser.getHeaderNames();
            collection = new CsvCollection(columnNames, records);
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by Header names
                Map<String,String> record = new HashMap<>();
                for (String columnName : columnNames) {
                    record.put(columnName, csvRecord.get(columnName));
                }
                records.add(record);
            }
        } catch (IOException e) {
            log.warn("Failed to read file: {}. Reason: {}", csvFilePath, e.getMessage());
            throw new MappingTableException("Failed to read file: " + csvFilePath  + " Reason: " + e.getMessage(),e);
        }
        return collection;
    }

    public static List<String> findTrendIds(String csvFilePath) {
        List<String> trendIds = new ArrayList<>();
        try (

                Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            List<String> columnNames = csvParser.getHeaderNames();
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by Header names
                String trendId = csvRecord.get("TrendId");
                trendIds.add(trendId);
            }
        } catch (IOException e) {
            log.warn("Failed to read file: {}. Reason: {}", csvFilePath, e.getMessage());
        }
        return trendIds;
    }

    public static Map<String, String> findObjectIds(String csvFilePath) {
        Map<String, String> objectReferences = new HashMap<>();
        try (

                Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            List<String> columnNames = csvParser.getHeaderNames();
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by Header names
                String objectId = csvRecord.get("MetasysObjectId");
                String metasysDbReference = csvRecord.get("MetasysObjectReference");
                objectReferences.put(objectId, metasysDbReference);
            }
        } catch (IOException e) {
            log.warn("Failed to read file: {}. Reason: {}", csvFilePath, e.getMessage());
            throw new MappingTableException("Failed to read file: " + csvFilePath  + " Reason: " + e.getMessage(),e);
        }
        return objectReferences;
    }

    public static Map<String,String> findObservationTypes(String csvFilePath) {
        Map<String,String> trendIds = new HashMap<>();
        try (

                Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            List<String> columnNames = csvParser.getHeaderNames();
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by Header names
                String trendId = csvRecord.get("observationType");
                String lastUpdated = csvRecord.get("lastImportAt");
                trendIds.put(trendId, lastUpdated);
            }
        } catch (IOException e) {
            log.warn("Failed to read file: {}. Reason: {}", csvFilePath, e.getMessage());
        }
        return trendIds;
    }

    public static void main(String[] args) {
        String filePath = "import-data/flow.csv";
        List<String> trendIds = findTrendIds(filePath);
        log.info("Found {} TrendIds", trendIds.size());
    }
}

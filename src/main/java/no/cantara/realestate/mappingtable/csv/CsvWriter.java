package no.cantara.realestate.mappingtable.csv;

import no.cantara.realestate.mappingtable.rec.SensorRecObject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.Set;

import static org.slf4j.LoggerFactory.getLogger;

public class CsvWriter {
    private static final Logger log = getLogger(CsvWriter.class);
    private final CSVPrinter printer;

    public CsvWriter(File exportFile, boolean append, String... headerNames) throws IOException {
        try {
            printer = new CSVPrinter(new FileWriter(exportFile,append), CSVFormat.RFC4180);
            printer.printRecord(headerNames);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public CsvWriter(CSVPrinter printer) {
        this.printer = printer;
    }

    public void writeLastImported(String trendId) {
        try {
            printer.printRecord(trendId, Instant.now());
        } catch (IOException e) {
            log.info("Failed to update trendId: {}. Reason: {}", trendId, e.getMessage());
        }
    }
    public void writeLastImported(String observationType, Instant lastUpdatedAt) {
        try {
            printer.printRecord(observationType, lastUpdatedAt);
        } catch (IOException e) {
            log.info("Failed to update observatonType: {}. Reason: {}", observationType, e.getMessage());
        }
    }

    public void writeMetasysReferenceObjectId(String metasysDbReference, String metasysObjectId, Instant lastImportAt) {
        try {
            printer.printRecord(metasysDbReference, metasysObjectId, lastImportAt);
        } catch (IOException e) {
            log.info("Failed to write MetasysReferenceObjectId: {}, {}. Reason: {}", metasysDbReference, metasysObjectId, e.getMessage());
        }
    }

    public void writeValues(String... values) {
        try {
            printer.printRecord(values);
        } catch (IOException e) {
            log.info("Failed to write values: {}, {}. Reason: {}", values, e.getMessage());
        }
    }

    public void add(Set<SensorRecObject> sensors) {
        try {
            for (SensorRecObject sensor : sensors) {
                printer.printRecord(sensor.getRecId());
            }
        } catch (IOException ex) {
            //TODO implement when needed.
            ex.printStackTrace();
        }
    }

    public void flush() {
        if (printer != null) {
            try {
                printer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        flush();
    }


}

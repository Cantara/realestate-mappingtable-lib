package no.cantara.realestate.mappingtable.csv;

import java.util.List;
import java.util.Map;

public class CsvCollection {

    private final List<String> columnNames;
    private final List<Map<String, String>> records;


    public CsvCollection(List<String> columnNames, List<Map<String, String>> records) {
        this.columnNames = columnNames;
        this.records = records;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public List<Map<String, String>> getRecords() {
        return records;
    }

    public void addRecord(Map<String, String> record) {
        if (records != null) {
            records.add(record);
        }
    }

}

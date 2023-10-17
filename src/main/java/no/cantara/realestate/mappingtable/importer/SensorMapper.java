package no.cantara.realestate.mappingtable.importer;

import no.cantara.realestate.mappingtable.rec.SensorRecObject;
import no.cantara.realestate.mappingtable.tfm.Tfm;

import java.util.List;
import java.util.Map;

public class SensorMapper {
    public static SensorRecObject importSensorRecObject(List<String> columnNames, Map<String, String> record) {
        SensorRecObject sensorRecObject = new SensorRecObject(record.get("RecId"));
        if (columnNames.contains("Tfm")) {
            Tfm tfm = new Tfm(record.get("Tfm"));
            sensorRecObject.setTfm(tfm);
        }
        if (columnNames.contains("Name")) {
            sensorRecObject.setName(record.get("Name"));
        }
        if (columnNames.contains("Description")) {
            sensorRecObject.setDescription(record.get("Description"));
        }
        if (columnNames.contains("RealEstate")) {
            sensorRecObject.setRealEstate(record.get("RealEstate"));
        }
        if (columnNames.contains("Building")) {
            sensorRecObject.setBuilding(record.get("Building"));
        }
        if (columnNames.contains("Floor")) {
            sensorRecObject.setFloor(record.get("Floor"));
        }
        if (columnNames.contains("Section")) {
            sensorRecObject.setSection(record.get("Section"));
        }
        if (columnNames.contains("ServesRoom")) {
            sensorRecObject.setServesRoom(record.get("ServesRoom"));
        }
        if (columnNames.contains("PlacementRoom")) {
            sensorRecObject.setPlacementRoom(record.get("PlacementRoom"));
        }
        if (columnNames.contains("ElectricityZone")) {
            sensorRecObject.setElectricityZone(record.get("ElectricityZone"));
        }
        if (columnNames.contains("ClimateZone")) {
            sensorRecObject.setClimateZone(record.get("ClimateZone"));
        }
        if (columnNames.contains("SensorType")) {
            sensorRecObject.setSensorType(record.get("SensorType"));
        }
        if (columnNames.contains("MeasurementUnit")) {
            sensorRecObject.setMeasurementUnit(record.get("MeasurementUnit"));
        }
        return sensorRecObject;
    }
}

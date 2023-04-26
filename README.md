# realestate-mappingtable-lib
Mapping sensors from eg BACNet to Real Estate Core

Mapping from SensorId to Location and SensorType.
* SensorId may be in multiple formats like BACNet, Metasys, Schneider EcoStruxure.
* Location placing the sensor inside a RealEstate.
* SensorType will create a uniform model of what a given sensor is observing.

## Ingestion Plugins
Eg. Metasys, BACNet, Modbus, Schneider EcoStruxure, etc.

### Implementation
These plugins must implement the following interface:

```java
public interface UniqueKey<T> {
    T getKey();
}
```
Every implementation of the UniqueKey must implement Equals and HashCode methods.
See the example of BacnetUniqueKey below:
```java 
public class BacnetUniqueKey implements UniqueKey<String> {

    private Integer deviceId;
    private Integer instanceId;
    private String objectType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BacnetSensorId that = (BacnetSensorId) o;
        return Objects.equals(getDeviceId(), that.getDeviceId()) && Objects.equals(getInstanceId(), that.getInstanceId()) 
                && Objects.equals(getObjectType(), that.getObjectType()) 
                && Objects.equals(getObjectName(), that.getObjectName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDeviceId(), getInstanceId(), getObjectType(), getObjectName());
    }
}
```

### Import data 
You may import data from CSV file. The import must be defined as such:
TODO: need clarification  
```java
public class BacnetImport implements Import<BacnetSensor> {
    @Override
    public List<BacnetSensor> importData() {
        List<BacnetSensor> bacnetSensors = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("bacnet.csv"))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                bacnetSensors.add(new BacnetSensor(line[0], line[1], line[2], line[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bacnetSensors;
    }
}
```




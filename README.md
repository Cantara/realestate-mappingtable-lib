# realestate-mappingtable-app
Mapping sensors from eg BACNet to Real Estate Core

## Ingestion Plugins
Eg. Metasys, BACNet, Modbus, Schneider EcoStruxure, etc.

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


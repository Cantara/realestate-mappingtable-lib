package no.cantara.realestate.mappingtable.bacnet;

import no.cantara.realestate.mappingtable.MappingKey;

import java.util.Objects;

public class BacnetMappingKey implements MappingKey<String> {

    private Integer deviceId;
    private Integer instanceId;
    private String objectType;

    public BacnetMappingKey(Integer deviceId, Integer instanceId, String objectType) {
        this.deviceId = deviceId;
        this.instanceId = instanceId;
        this.objectType = objectType;
    }

    @Override
    public String getKey() {
        return "BACNet__" + deviceId+ "__" + instanceId+ "__" + objectType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BacnetMappingKey that = (BacnetMappingKey) o;
        return Objects.equals(deviceId, that.deviceId) && Objects.equals(instanceId, that.instanceId) && Objects.equals(objectType, that.objectType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, instanceId, objectType);
    }
}

package no.cantara.realestate.mappingtable.bacnet;

import no.cantara.realestate.mappingtable.UniqueKey;

import java.util.Objects;

public class BacnetUniqueKey implements UniqueKey<String> {

    private Integer deviceId;
    private Integer instanceId;
    private String objectType;

    public BacnetUniqueKey(Integer deviceId, Integer instanceId, String objectType) {
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
        BacnetUniqueKey that = (BacnetUniqueKey) o;
        return Objects.equals(deviceId, that.deviceId) && Objects.equals(instanceId, that.instanceId) && Objects.equals(objectType, that.objectType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, instanceId, objectType);
    }
}

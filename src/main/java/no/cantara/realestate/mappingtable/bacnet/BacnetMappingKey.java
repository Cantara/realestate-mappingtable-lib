package no.cantara.realestate.mappingtable.bacnet;

import no.cantara.realestate.mappingtable.MappingKey;

public class BacnetMappingKey implements MappingKey<BacnetSensorId> {

    private Integer deviceId;
    private Integer instanceId;
    private String objectType;

    public BacnetMappingKey(Integer deviceId, Integer instanceId, String objectType) {
        this.deviceId = deviceId;
        this.instanceId = instanceId;
        this.objectType = objectType;
    }

    @Override
    public BacnetSensorId getKey() {
        return new BacnetSensorId(deviceId,instanceId,objectType);
    }
}

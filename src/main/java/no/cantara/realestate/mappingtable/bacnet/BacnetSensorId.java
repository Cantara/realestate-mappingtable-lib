package no.cantara.realestate.mappingtable.bacnet;

import no.cantara.realestate.mappingtable.SensorId;
import no.cantara.realestate.mappingtable.UniqueKey;

import java.util.Objects;

/**
 * Bacnet Sensor may be identified by how a sensor is attached to a controller.
 * The controller has an IP address, yet it is also identified by a device id.
 * The sensor is identified by a combination of  instance id and Object Type.
 */
@Deprecated // Use no.cantara.realestate.sensors.bacnet.BacnetSensorId from "typelib-java" instead
public class BacnetSensorId extends SensorId {
    private Integer deviceId; // Eg. Controller or "Undersentral"
    private Integer instanceId; // Eg. "Punkt" or "Punktgruppe"
    private String ObjectType;
    private String ObjectName; //May contain the TFM code, or maybe a mac address.

    public BacnetSensorId(Integer deviceId, Integer instanceId, String objectType) {
        this.deviceId = deviceId;
        this.instanceId = instanceId;
        ObjectType = objectType;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Integer instanceId) {
        this.instanceId = instanceId;
    }

    public String getObjectType() {
        return ObjectType;
    }

    public void setObjectType(String objectType) {
        ObjectType = objectType;
    }

    public String getObjectName() {
        return ObjectName;
    }

    public void setObjectName(String objectName) {
        ObjectName = objectName;
    }

    @Override
    public UniqueKey getMappingKey() {
        return new BacnetUniqueKey(deviceId,instanceId,ObjectType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BacnetSensorId that = (BacnetSensorId) o;
        return Objects.equals(getDeviceId(), that.getDeviceId()) && Objects.equals(getInstanceId(), that.getInstanceId()) && Objects.equals(getObjectType(), that.getObjectType()) && Objects.equals(getObjectName(), that.getObjectName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDeviceId(), getInstanceId(), getObjectType(), getObjectName());
    }
}

package no.cantara.realestate.mappingtable;

import no.cantara.realestate.mappingtable.rec.SensorRecObject;

@Deprecated //use no.cantara.realestate.sensors.MappedSensorId from "typelib-java" in stead
public class MappedSensorId {

    private final SensorId sensorId;
    private final SensorRecObject rec;

    public MappedSensorId(SensorId sensorId, SensorRecObject rec) {
        this.sensorId = sensorId;
        this.rec = rec;
    }

    public SensorId getSensorId() {
        return sensorId;
    }

    public SensorRecObject getRec() {
        return rec;
    }

    @Override
    public String toString() {
        return "MappedSensorId{" +
                "sensorId=" + sensorId +
                ", rec=" + rec +
                '}';
    }
}

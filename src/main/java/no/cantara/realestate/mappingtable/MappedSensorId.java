package no.cantara.realestate.mappingtable;

import no.cantara.realestate.mappingtable.rec.RecObject;
import no.cantara.realestate.mappingtable.rec.SensorRecObject;

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

    public RecObject getRec() {
        return rec;
    }
}
package no.cantara.realestate.mappingtable;

import no.cantara.realestate.mappingtable.rec.RecObject;

public class MappedSensorId {

    private final SensorId sensorId;
    private final RecObject rec;

    public MappedSensorId(SensorId sensorId, RecObject rec) {
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

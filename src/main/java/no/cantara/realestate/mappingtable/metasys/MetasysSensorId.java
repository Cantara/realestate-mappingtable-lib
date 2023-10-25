package no.cantara.realestate.mappingtable.metasys;

import no.cantara.realestate.mappingtable.SensorId;


@Deprecated // Use no.cantara.realestate.sensors.metasys.MetasysSensorId from "typelib-java" instead
public class MetasysSensorId extends SensorId {

    private final String metasysObjectId;
    private final String metasysObjectReference;

    public MetasysSensorId(String metasysObjectId, String metasysObjectReference) {
        this.metasysObjectId = metasysObjectId;
        this.metasysObjectReference = metasysObjectReference;
    }

    public String getMetasysObjectId() {
        return metasysObjectId;
    }

    public String getMetasysObjectReference() {
        return metasysObjectReference;
    }

    public MetasysUniqueKey getMappingKey() {
        return new MetasysUniqueKey(metasysObjectId);
    }

    @Override
    public String toString() {
        return "MetasysSensorId{" +
                "metasysObjectId='" + metasysObjectId + '\'' +
                ", metasysObjectReference='" + metasysObjectReference + '\'' +
                "} " + super.toString();
    }
}

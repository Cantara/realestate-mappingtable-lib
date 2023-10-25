package no.cantara.realestate.mappingtable.desigo;

import no.cantara.realestate.mappingtable.SensorId;

import java.util.Objects;

@Deprecated // Use no.cantara.realestate.sensors.desigo.DesigoSensorId from "typelib-java" instead
public class DesigoSensorId extends SensorId {

    private final String desigoId;
    private final String desigoPropertyId;

    private String trendId = null;


    public DesigoSensorId(String desigoId, String desigoPropertyId) {
        this.desigoId = desigoId;
        this.desigoPropertyId = desigoPropertyId;
    }

    public DesigoUniqueKey getMappingKey() {
        return new DesigoUniqueKey(desigoId, desigoPropertyId);
    }

    public String getDesigoId() {
        return desigoId;
    }

    public String getDesigoPropertyId() {
        return desigoPropertyId;
    }

    public String getTrendId() {
        return trendId;
    }

    public void setTrendId(String trendId) {
        this.trendId = trendId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DesigoSensorId that = (DesigoSensorId) o;
        return Objects.equals(desigoId, that.desigoId) && Objects.equals(desigoPropertyId, that.desigoPropertyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desigoId, desigoPropertyId);
    }

    @Override
    public String toString() {
        return "DesigoSensorId{" +
                "desigoId='" + desigoId + '\'' +
                ", desigoPropertyId='" + desigoPropertyId + '\'' +
                ", trendId='" + trendId + '\'' +
                "} " + super.toString();
    }
}

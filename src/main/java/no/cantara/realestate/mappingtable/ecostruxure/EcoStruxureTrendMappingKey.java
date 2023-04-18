package no.cantara.realestate.mappingtable.ecostruxure;

import no.cantara.realestate.mappingtable.MappingKey;

public class EcoStruxureTrendMappingKey implements MappingKey<EcoStruxureTrendSensorId> {
    private final String trendId;

    public EcoStruxureTrendMappingKey(String trendId) {
        this.trendId = trendId;
    }

    public String getTrendId() {
        return trendId;
    }

    @Override
    public EcoStruxureTrendSensorId getKey() {
        return new EcoStruxureTrendSensorId(trendId);
    }
}

package no.cantara.realestate.mappingtable.ecostruxure;

import no.cantara.realestate.mappingtable.MappingKey;

import java.util.Objects;

public class EcoStruxureTrendMappingKey implements MappingKey<String> {
    private final String trendId;

    public EcoStruxureTrendMappingKey(String trendId) {
        this.trendId = trendId;
    }

    public String getTrendId() {
        return trendId;
    }

    @Override
    public String getKey() {
        return "EcoStruxure__" + trendId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EcoStruxureTrendMappingKey that = (EcoStruxureTrendMappingKey) o;
        return Objects.equals(getTrendId(), that.getTrendId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrendId());
    }
}

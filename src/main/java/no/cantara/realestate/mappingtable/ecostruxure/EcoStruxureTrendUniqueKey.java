package no.cantara.realestate.mappingtable.ecostruxure;

import no.cantara.realestate.mappingtable.UniqueKey;

import java.util.Objects;

public class EcoStruxureTrendUniqueKey implements UniqueKey<String> {
    private final String trendId;

    public EcoStruxureTrendUniqueKey(String trendId) {
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
        EcoStruxureTrendUniqueKey that = (EcoStruxureTrendUniqueKey) o;
        return Objects.equals(getTrendId(), that.getTrendId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrendId());
    }
}

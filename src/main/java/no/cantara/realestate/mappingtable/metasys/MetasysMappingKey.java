package no.cantara.realestate.mappingtable.metasys;

import no.cantara.realestate.mappingtable.MappingKey;

import java.util.Objects;

public class MetasysMappingKey implements MappingKey<String> {
    private final String metasysDbId;
    public MetasysMappingKey(String metasysDbId) {
        this.metasysDbId = metasysDbId;
    }

    public String getKey() {
        return "Metasys__" + metasysDbId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetasysMappingKey that = (MetasysMappingKey) o;
        return Objects.equals(metasysDbId, that.metasysDbId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metasysDbId);
    }
}

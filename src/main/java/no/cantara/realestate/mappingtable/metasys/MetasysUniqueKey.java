package no.cantara.realestate.mappingtable.metasys;

import no.cantara.realestate.mappingtable.UniqueKey;

import java.util.Objects;

public class MetasysUniqueKey implements UniqueKey<String> {
    private final String metasysDbId;
    public MetasysUniqueKey(String metasysDbId) {
        this.metasysDbId = metasysDbId;
    }

    public String getKey() {
        return "Metasys__" + metasysDbId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetasysUniqueKey that = (MetasysUniqueKey) o;
        return Objects.equals(metasysDbId, that.metasysDbId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metasysDbId);
    }
}

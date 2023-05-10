package no.cantara.realestate.mappingtable.metasys;

import no.cantara.realestate.mappingtable.UniqueKey;

import java.util.Objects;

public class MetasysUniqueKey implements UniqueKey<String> {
    private final String metasysObjectId;
    public MetasysUniqueKey(String metasysObjectId) {
        this.metasysObjectId = metasysObjectId;
    }

    public String getKey() {
        return "Metasys__" + metasysObjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetasysUniqueKey that = (MetasysUniqueKey) o;
        return Objects.equals(metasysObjectId, that.metasysObjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metasysObjectId);
    }
}

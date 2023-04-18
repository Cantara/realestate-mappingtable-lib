package no.cantara.realestate.mappingtable.metasys;

import no.cantara.realestate.mappingtable.MappingKey;

public class MetasysMappingKey implements MappingKey<String> {
    private final String metasysDbId;

    public MetasysMappingKey(String metasysDbId) {
        this.metasysDbId = metasysDbId;
    }

    public String getKey() {
        return metasysDbId;
    }
}

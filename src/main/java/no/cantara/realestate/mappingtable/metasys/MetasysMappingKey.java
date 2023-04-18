package no.cantara.realestate.mappingtable.metasys;

import no.cantara.realestate.mappingtable.MappingKey;

public class MetasysMappingKey implements MappingKey<MetasysSensorId> {
    private final String metasysDbId;
    private final String metasysObjectReference;

    public MetasysMappingKey(String metasysDbId, String metasysObjectReference) {
        this.metasysDbId = metasysDbId;
        this.metasysObjectReference = metasysObjectReference;
    }

    public MetasysSensorId getKey() {
        return new MetasysSensorId(metasysDbId,metasysObjectReference);
    }
}

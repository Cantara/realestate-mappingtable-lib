package no.cantara.realestate.mappingtable.tfm;

import no.cantara.realestate.mappingtable.MappingKey;

public class TfmMappingKey implements MappingKey<Tfm> {
    private final Tfm tfm;

    public TfmMappingKey(Tfm tfm) {
        this.tfm = tfm;
    }

    public Tfm getKey() {
        return tfm;
    }
}

package no.cantara.realestate.mappingtable;

import no.cantara.realestate.mappingtable.tfm.Tfm;

public class MappingKey {
    private final Tfm tfm;

    public MappingKey(Tfm tfm) {
        this.tfm = tfm;
    }

    public Tfm getTfm() {
        return tfm;
    }
}

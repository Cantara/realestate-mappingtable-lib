package no.cantara.realestate.mappingtable.tfm;

import no.cantara.realestate.mappingtable.UniqueKey;

public class TfmUniqueKey implements UniqueKey<Tfm> {
    private final Tfm tfm;

    public TfmUniqueKey(Tfm tfm) {
        this.tfm = tfm;
    }

    public Tfm getKey() {
        return tfm;
    }
}

package no.cantara.realestate.mappingtable.bacnet;

import no.cantara.realestate.mappingtable.tfm.Tfm;

public class BacnetMappingKey {
    private final Tfm tfm;

    public BacnetMappingKey(Tfm tfm) {
        this.tfm = tfm;
    }

    public Tfm getTfm() {
        return tfm;
    }
}

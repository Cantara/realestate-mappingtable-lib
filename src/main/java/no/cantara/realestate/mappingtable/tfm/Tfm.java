package no.cantara.realestate.mappingtable.tfm;

import java.util.Objects;

/*
Tverrfaglig Merkesytem is a tag describing which system a sensor or actuator do belong to.
Optionally this Tag may include positional information like placement room.
 */
public class Tfm {

    private final String tfm;

    public Tfm(String tfm) {
        this.tfm = tfm;
    }

    public String getTfm() {
        return tfm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tfm tfm1 = (Tfm) o;
        return Objects.equals(getTfm(), tfm1.getTfm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTfm());
    }
}

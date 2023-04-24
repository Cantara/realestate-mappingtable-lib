package no.cantara.realestate.mappingtable.repository;

import no.cantara.realestate.mappingtable.MappedSensorId;

import java.util.function.Predicate;

public class MappedIdQuery {

    private final Predicate<MappedSensorId> predicate;

    public MappedIdQuery(Predicate<MappedSensorId> predicate) {
        this.predicate = predicate;
    }

    public Predicate<MappedSensorId> getPredicate() {
        return predicate;
    }
}

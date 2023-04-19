package no.cantara.realestate.mappingtable;

public interface MappingKey<T> {
    T getKey();
    boolean equals(Object o);
    int hashCode();

}

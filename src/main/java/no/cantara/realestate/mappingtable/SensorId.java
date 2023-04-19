package no.cantara.realestate.mappingtable;

public abstract class SensorId {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract MappingKey getMappingKey();
}

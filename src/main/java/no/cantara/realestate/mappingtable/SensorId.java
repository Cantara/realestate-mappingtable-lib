package no.cantara.realestate.mappingtable;

@Deprecated // Use no.cantara.realestate.SensorId from "typelib-java" instead
public abstract class SensorId {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract UniqueKey getMappingKey();

    @Override
    public String toString() {
        return "SensorId{" +
                "id='" + id + '\'' +
                '}';
    }
}

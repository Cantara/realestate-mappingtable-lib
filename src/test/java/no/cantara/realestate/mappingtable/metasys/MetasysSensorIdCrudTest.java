package no.cantara.realestate.mappingtable.metasys;

import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.rec.RecObject;
import no.cantara.realestate.mappingtable.repository.MappedIdRepository;
import no.cantara.realestate.mappingtable.repository.MappedIdRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetasysSensorIdCrudTest {

    MappedIdRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MappedIdRepositoryImpl();
    }

    @Test
    void add() {
        assertEquals(0, repository.size());
        MetasysSensorId sensorId = new MetasysSensorId("dbId", "objectRef");
        RecObject recObject = new RecObject("recid");
        MappedSensorId mappedSensorId = new MappedSensorId(sensorId, recObject);
        repository.add(mappedSensorId);
        assertEquals(1, repository.size());
    }
}

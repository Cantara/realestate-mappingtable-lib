package no.cantara.realestate.mappingtable.repository;

import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.UniqueKey;
import no.cantara.realestate.mappingtable.metasys.MetasysSensorId;
import no.cantara.realestate.mappingtable.rec.SensorRecObject;
import no.cantara.realestate.mappingtable.tfm.Tfm;
import no.cantara.realestate.mappingtable.tfm.TfmUniqueKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MappedIdRepositoryImplTest {

    MappedIdRepository repository;
    final Tfm tfm = new Tfm("TFM-RY02101");

    @BeforeEach
    void setUp() {
        repository = new MappedIdRepositoryImpl();
    }

    @Test
    void add() {
        assertEquals(0, repository.size());
        MetasysSensorId sensorId = new MetasysSensorId("dbId", "objectRef");
        SensorRecObject recObject = new SensorRecObject("recid");
        MappedSensorId mappedSensorId = new MappedSensorId(sensorId, recObject);
        repository.add(mappedSensorId);
        assertEquals(1, repository.size());
    }

    @Test
    void find() {
        assertEquals(0, repository.size());
        MetasysSensorId sensorId = new MetasysSensorId("dbId", "objectRef");
        SensorRecObject recObject = new SensorRecObject("recid");
        recObject.setTfm(tfm);
        MappedSensorId mappedSensorId = new MappedSensorId(sensorId, recObject);
        repository.add(mappedSensorId);
        MetasysSensorId tempSensor = new MetasysSensorId("dbId2", "objectRef2");
        SensorRecObject tempRec = new SensorRecObject("tempRec");
        MappedSensorId tempSensorId = new MappedSensorId(tempSensor, tempRec);
        repository.add(tempSensorId);
        assertEquals(2, repository.size());
        UniqueKey tfmKey = new TfmUniqueKey(tfm);
        List<MappedSensorId> matchingSersorIds = repository.find(tfmKey);
        assertNotNull(matchingSersorIds);
        assertEquals(1, matchingSersorIds.size());
        assertEquals("recid", matchingSersorIds.get(0).getRec().getRecId());
    }

    @Test
    void updateRec() {
        assertEquals(0, repository.size());
        MetasysSensorId sensorId = new MetasysSensorId("dbId", "objectRef");
        SensorRecObject recObject = new SensorRecObject("recid");
        MappedSensorId mappedSensorId = new MappedSensorId(sensorId, recObject);
        repository.add(mappedSensorId);

        recObject.setTfm(tfm);
        repository.updateRec(recObject);
        assertEquals(1, repository.size());
    }
}
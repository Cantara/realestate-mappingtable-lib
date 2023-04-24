package no.cantara.realestate.mappingtable.repository;

import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.bacnet.BacnetSensorId;
import no.cantara.realestate.mappingtable.bacnet.BacnetUniqueKey;
import no.cantara.realestate.mappingtable.ecostruxure.EcoStruxureTrendSensorId;
import no.cantara.realestate.mappingtable.ecostruxure.EcoStruxureTrendUniqueKey;
import no.cantara.realestate.mappingtable.metasys.MetasysSensorId;
import no.cantara.realestate.mappingtable.metasys.MetasysUniqueKey;
import no.cantara.realestate.mappingtable.rec.SensorRecObject;
import no.cantara.realestate.mappingtable.tfm.Tfm;
import no.cantara.realestate.mappingtable.tfm.TfmUniqueKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecAttributeFindSensorRepositoryTest {

    MappedIdRepository repository;
//    final Tfm tfm = new Tfm("TFM-RY02101");
    int expectedCount = 0;

    @BeforeEach
    void setUp() {
        repository = new MappedIdRepositoryImpl();
        MappedSensorId metasysMappedId = buildMetasysMappedId("dbId1", "objectRef1", "recid1", "TFM-1",
                "msre1","building1","floor1","room1","CO2","ppm");
        repository.add(metasysMappedId);
        repository.add(buildMetasysMappedId("dbId2", "objectRef2", "recid2", "TFM-2",
                "msre1","building1","floor1","room1","temp","C"));
        repository.add(buildMetasysMappedId("dbId3", "objectRef3", "recid3", "TFM-3",
                "msre1","building1","floor1","room2","CO2","ppm"));
        repository.add(buildMetasysMappedId("dbId4", "objectRef4", "recid21", "TFM-21",
                "msre1","building1","floor1","room2","temp","C"));
        MappedSensorId bacnetMappedId = buildBacnetMappedId(1001, 345006, "AnalogValue", "recId4", "TFM-4",
                        "msre2","building1","floor1","room1","temp","C");
        repository.add(bacnetMappedId);
        repository.add(buildBacnetMappedId(1002, 345006, "AnalogValue", "recId5", "TFM-5",
                "msre2","building1","floor1","room1","CO2","ppm"));
        repository.add(buildBacnetMappedId(1001, 345007, "AnalogValue", "recId6", "TFM-6",
                "msre2","building1","floor1","room2","temp","C"));
        repository.add(buildBacnetMappedId(1001, 345008, "DigitalInput", "recId7", "TFM-7",
                "msre2","building1","floor1","room2","co2","ppm"));
        MappedSensorId ecoStruxureTrendMappedId = buildEcoStruxureTrendMappedId("TrendId1", "recId8", "TFM-8",
                "msre3","building2","floor10","room1","Electricity","kwh");
        repository.add(ecoStruxureTrendMappedId);
        expectedCount = 8;
    }

    private MappedSensorId buildEcoStruxureTrendMappedId(String trendId, String recId, String tfm, String realEstate, String building, String floor, String placementRoom, String sensorType, String measurementUnit) {
        EcoStruxureTrendSensorId sensorId = new EcoStruxureTrendSensorId(trendId);
        SensorRecObject recObject = buildRecObject(recId, tfm, realEstate, building, floor, placementRoom, sensorType, measurementUnit);
        return new MappedSensorId(sensorId, recObject);
    }

    private MappedSensorId buildBacnetMappedId(int deviceId, int instanceId, String objectType, String recId, String tfm, String realEstate, String building, String floor, String placementRoom, String sensorType, String measurementUnit) {
        BacnetSensorId sensorId = new BacnetSensorId(deviceId, instanceId, objectType);
        SensorRecObject recObject = buildRecObject(recId, tfm, realEstate, building, floor, placementRoom, sensorType, measurementUnit);
        return new MappedSensorId(sensorId, recObject);
    }

    MappedSensorId buildMetasysMappedId(String metasysDbId, String metasysObjectReference, String recId, String tfm, String realEstate, String building, String floor, String placementRoom, String sensorType, String measurementUnit) {
        MetasysSensorId sensorId = new MetasysSensorId(metasysDbId, metasysObjectReference);
        SensorRecObject recObject = buildRecObject(recId, tfm, realEstate, building, floor, placementRoom, sensorType, measurementUnit);
        return new MappedSensorId(sensorId, recObject);
    }

    SensorRecObject buildRecObject(String recId, String tfm, String realEstate, String building, String floor, String placementRoom, String sensorType, String measurementUnit) {
        SensorRecObject recObject = new SensorRecObject(recId);
        recObject.setTfm(new Tfm(tfm));
        recObject.setRealEstate(realEstate);
        recObject.setBuilding(building);
        recObject.setFloor(floor);
        recObject.setPlacementRoom(placementRoom);
        recObject.setSensorType(sensorType);
        recObject.setMeasurementUnit(measurementUnit);
        return recObject;
    }

    @Test
    void findUsingMappingKey() {
        assertEquals(9, repository.size());
        List<MappedSensorId> matchingSersorIds = repository.find(new TfmUniqueKey(new Tfm("TFM-7")));
        assertEquals(1, matchingSersorIds.size());
        matchingSersorIds = repository.find(new MetasysUniqueKey("dbId1"));
        assertEquals(1, matchingSersorIds.size());
        matchingSersorIds = repository.find(new BacnetUniqueKey(1001, 345008, "DigitalInput"));
        assertEquals(1, matchingSersorIds.size());
        matchingSersorIds = repository.find(new EcoStruxureTrendUniqueKey("TrendId1"));
        assertEquals(1, matchingSersorIds.size());
        assertEquals("recId8", matchingSersorIds.get(0).getRec().getRecId());
        assertEquals("TFM-8", matchingSersorIds.get(0).getRec().getTfm().getTfm());
        matchingSersorIds = repository.find(new EcoStruxureTrendUniqueKey("TrendId0"));
        assertEquals(0, matchingSersorIds.size());
    }

    @Test
    void findBasedOnRecObjectAttributes() {
        MappedIdQuery query = new MappedIdQueryBuilder().realEstate("msre1").build();
        List<MappedSensorId> matchingSersorIds = repository.find(query);
        assertEquals(4, matchingSersorIds.size());
        query = new MappedIdQueryBuilder().realEstate("msre1").placementRoom("room1").build();
        assertEquals(2, repository.find(query).size());
    }

    @Test
    void findRecObjectAndSensorIdType() {
        MappedIdQuery query = new MappedIdQueryBuilder().sensorIdClass(MetasysSensorId.class).build();
        List<MappedSensorId> matchingSersorIds = repository.find(query);
        assertEquals(4, matchingSersorIds.size());
        query = new MappedIdQueryBuilder().realEstate("msre1").placementRoom("room1").build();
        assertEquals(2, repository.find(query).size());
        query = new MappedIdQueryBuilder().sensorIdClass(EcoStruxureTrendSensorId.class).build();
        assertEquals(1, repository.find(query).size());
    }
}

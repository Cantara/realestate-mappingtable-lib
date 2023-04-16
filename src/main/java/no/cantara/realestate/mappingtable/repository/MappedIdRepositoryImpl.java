package no.cantara.realestate.mappingtable.repository;

import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.MappingKey;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MappedIdRepositoryImpl implements MappedIdRepository {
    private static final Logger log = getLogger(MappedIdRepositoryImpl.class);

    List<MappedSensorId> sensorIds = new ArrayList<>();
    @Override
    public void add(MappedSensorId sensorId) {
        if (sensorId != null) {
            sensorIds.add(sensorId);
        }
    }

    @Override
    public List<MappedSensorId> find(MappingKey mappingKey) {
        return null;
    }

    @Override
    public long size() {
        if (sensorIds == null) {
            return 0;
        }
        return sensorIds.size() ;
    }
}

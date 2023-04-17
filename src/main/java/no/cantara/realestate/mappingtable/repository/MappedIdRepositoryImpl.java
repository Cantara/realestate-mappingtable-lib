package no.cantara.realestate.mappingtable.repository;

import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.MappingKey;
import no.cantara.realestate.mappingtable.rec.RecObject;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        List<MappedSensorId> matching = null;
        if ( mappingKey != null && mappingKey.getTfm() != null) {
            matching = sensorIds.stream()
                    .filter(Objects::nonNull)
                    .filter(r -> Objects.nonNull(r.getRec().getTfm()))
                    .filter(r -> r.getRec().getTfm().equals(mappingKey.getTfm()))
                    .collect(Collectors.toList());
        }
        return matching;
    }

    @Override
    public long updateRec(RecObject recObject) {
        return 0;
    }

    @Override
    public long size() {
        if (sensorIds == null) {
            return 0;
        }
        return sensorIds.size() ;
    }
}

package no.cantara.realestate.mappingtable.repository;

import no.cantara.realestate.mappingtable.MappedSensorId;
import no.cantara.realestate.mappingtable.MappingKey;
import no.cantara.realestate.mappingtable.bacnet.BacnetSensorId;
import no.cantara.realestate.mappingtable.ecostruxure.EcoStruxureTrendSensorId;
import no.cantara.realestate.mappingtable.metasys.MetasysSensorId;
import no.cantara.realestate.mappingtable.rec.RecObject;
import no.cantara.realestate.mappingtable.tfm.Tfm;
import org.slf4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
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
        if ( mappingKey != null && mappingKey.getKey() != null) {
            if (mappingKey.getKey() instanceof Tfm) {
                matching = sensorIds.stream()
                        .filter(Objects::nonNull)
                        .filter(r -> Objects.nonNull(r.getRec().getTfm()))
                        .filter(r -> r.getRec().getTfm().equals(mappingKey.getKey()))
                        .collect(Collectors.toList());
                //#25 TODO implement generic for ingestion plugins
            } else if (mappingKey.getKey() instanceof BacnetSensorId) {
                throw new NotImplementedException();
            } else if (mappingKey.getKey() instanceof MetasysSensorId) {
                throw new NotImplementedException();
            } else if (mappingKey.getKey() instanceof EcoStruxureTrendSensorId) {
                throw new NotImplementedException();
            } else {
                throw new NotImplementedException();
            }
        }
//        TODO expect to use Predicates?
//         matching = filterByMappingKey(sensorIds, k -> k.getKey().equals(mappingKey.getKey()));
        return matching;
    }

    protected static <T> List<T> filterByMappingKey(Collection<T> collection, Predicate<MappingKey> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : collection) {
            if (predicate.test((MappingKey)item)) {
                result.add(item);
            }
        }
        return result;
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

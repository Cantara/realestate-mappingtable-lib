package no.cantara.realestate.mappingtable;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import no.cantara.config.ApplicationProperties;
import no.cantara.realestate.mappingtable.importer.CsvSensorImporter;
import no.cantara.realestate.mappingtable.metasys.MetasysCsvSensorImporter;
import no.cantara.realestate.mappingtable.repository.MappedIdRepository;
import no.cantara.realestate.mappingtable.repository.MappedIdRepositoryImpl;
import org.slf4j.Logger;

import java.nio.file.Paths;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Hello world!
 */
public class Main {
    private static final Logger log = getLogger(Main.class);

    private MappedIdRepository mappedIdRepository;

    boolean loadPlugins() {
        boolean loadedOk = false;
        try {
            String configDirectory = "import-data";
            if (!Paths.get(configDirectory).toFile().exists()) {
                throw new MappingTableException("Import of data from " + configDirectory + " failed. Directory does not exist.");
            }
            CsvSensorImporter csvSensorImporter = new MetasysCsvSensorImporter(Paths.get(configDirectory).toFile());
            List<MappedSensorId> mappedSensorIds = csvSensorImporter.importMappedId("metasys");
            for (MappedSensorId mappedSensorId : mappedSensorIds) {
                mappedIdRepository.add(mappedSensorId);
            }
            loadedOk = true;
        } catch (Exception e) {
            MappingTableException me = new MappingTableException("Failed to load plugins",e);
            log.error("Failure loading plugins. {}", me.getMessage(), e);
        }
        return loadedOk;
    }

    public static ObjectMapper initializeObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.configOverride(ObservationMessage.class).setInclude(JsonInclude.Value.construct(JsonInclude.Include.NON_NULL, JsonInclude.Include.NON_NULL));
        return objectMapper;
    }

    public static ApplicationProperties getConfig() {
        return ApplicationProperties.getInstance();
    }

    public static String getConfigValue(String key) {
        return ApplicationProperties.getInstance().get(key);
    }
    public static void main(String[] args) {
        Main main = new Main();
        main.initialize();
        if (main.loadPlugins()) {
            log.info("Plugins loaded ok.");
        } else {
            log.error("Failed to load plugins");
        }
        log.info("Loaded {} sensors.", main.getMappedIdRepository().size());
    }

    private MappedIdRepository getMappedIdRepository() {
        return mappedIdRepository;
    }

    void initialize() {
        mappedIdRepository = new MappedIdRepositoryImpl();
    }

}

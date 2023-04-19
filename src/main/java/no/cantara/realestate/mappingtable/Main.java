package no.cantara.realestate.mappingtable;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import no.cantara.config.ApplicationProperties;

/**
 * Hello world!
 */
public class Main {

    boolean loadPlugins() {
        return true;
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
        if (main.loadPlugins()) {
            System.out.println("Plugins loaded ok");
        } else {
            System.err.println("Failed to load plugins");
        }
    }

}

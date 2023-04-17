package no.cantara.realestate.mappingtable;

import org.slf4j.helpers.MessageFormatter;

import java.util.UUID;

public class MappingTableException extends RuntimeException {
    private final UUID uuid;

    public MappingTableException(String message) {
        super(message);
        uuid = UUID.randomUUID();
    }

    public MappingTableException(String message, Throwable throwable) {
        super(message, throwable);
        this.uuid = UUID.randomUUID();
    }

    public MappingTableException(String message, Throwable throwable, Object... parameters) {
        this(MessageFormatter.format(message, parameters).getMessage(),throwable);

    }

    @Override
    public String getMessage() {

        String message = super.getMessage() +" MessageId: " + uuid.toString();
        if (getCause() != null) {
            message = message + "\n\tCause: " + getCause().getMessage();
        }
        return message;
    }

    public String getMessageId() {
        return uuid.toString();
    }


}


package io.gnelsimonyan.users.rest.exceptionhandlers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor(staticName = "of")
public class ExceptionResponse {

    private final String message;

    private Map<String, Object> metadata;

    static ExceptionResponse of(final String message) {
        return new ExceptionResponse(message, new HashMap<>());
    }

    public ExceptionResponse putMetadata(final String key, final Object value) {
        if (metadata == null) {
            metadata = new HashMap<>();
        }

        metadata.put(key, value);

        return this;
    }
}

package io.gnelsimonyan.notes.exceptions;

import lombok.Getter;

@Getter
public class InvalidParameterException extends Exception {
    private String field;

    public InvalidParameterException(final String message) {
        super(message);
    }

    public InvalidParameterException(final String message, final String field) {
        super(message);
        this.field = field;
    }
}

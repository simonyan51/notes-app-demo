package io.gnelsimonyan.notes.rest.common;

public enum ResponseStatus {
    SUCCESS("success"),
    FAILED("failed");

    public final String value;

    ResponseStatus(String value) {
        this.value = value;
    }
}

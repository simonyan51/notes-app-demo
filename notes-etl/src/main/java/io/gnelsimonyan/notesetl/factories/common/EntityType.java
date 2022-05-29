package io.gnelsimonyan.notesetl.factories.common;

public enum EntityType {
    NOTES("notes");

    private String type;

    EntityType(final String type) {
        this.type = type;
    }
}

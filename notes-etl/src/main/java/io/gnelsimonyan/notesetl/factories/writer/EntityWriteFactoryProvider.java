package io.gnelsimonyan.notesetl.factories.writer;

import io.gnelsimonyan.notesetl.factories.common.EntityType;
import io.gnelsimonyan.notesetl.factories.writer.abstractions.EntityWriteFactory;

public interface EntityWriteFactoryProvider {

    static EntityWriteFactory getEntityWriteFactory(final EntityType entityType) {
        return switch (entityType) {
            case NOTES -> new NoteEntityWriteFactory();
        };
    }
}

package io.gnelsimonyan.notesetl.factories.processor;

import io.gnelsimonyan.notesetl.factories.common.EntityType;
import io.gnelsimonyan.notesetl.factories.processor.abstractions.ItemProcessorFactory;

public interface EntityItemProcessorProvider {
    static ItemProcessorFactory getEntityItemProcessorFactory(final EntityType entityType) {
        return switch (entityType) {
            case NOTES -> new NotesEntityItemProcessorFactory();
        };
    };
}

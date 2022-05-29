package io.gnelsimonyan.notesetl.factories.writer;

import io.gnelsimonyan.notesetl.factories.writer.abstractions.EntityWriteFactory;
import io.gnelsimonyan.notesetl.factories.writer.abstractions.ItemWriterFactory;
import io.gnelsimonyan.notesetl.factories.common.ProcessorType;

public class NoteEntityWriteFactory implements EntityWriteFactory {

    @Override
    public ItemWriterFactory getItemWriterFactory(final ProcessorType processorType) {
        return switch (processorType) {
            case JSON -> new NoteEntityJSONWriterFactory();
            case PARQUET -> new NoteEntityJSONWriterFactory();
        };
    }
}

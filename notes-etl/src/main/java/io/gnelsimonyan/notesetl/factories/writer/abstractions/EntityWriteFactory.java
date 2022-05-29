package io.gnelsimonyan.notesetl.factories.writer.abstractions;

import io.gnelsimonyan.notesetl.factories.common.ProcessorType;

public interface EntityWriteFactory {
    ItemWriterFactory getItemWriterFactory(ProcessorType processorType);
}

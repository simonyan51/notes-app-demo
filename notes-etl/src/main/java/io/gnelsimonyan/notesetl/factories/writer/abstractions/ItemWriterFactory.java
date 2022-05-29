package io.gnelsimonyan.notesetl.factories.writer.abstractions;

import io.gnelsimonyan.notesetl.factories.common.WriterType;
import org.springframework.batch.item.ItemWriter;

import java.io.IOException;

public interface ItemWriterFactory {
    <T> ItemWriter<T> getItemWriter(WriterType writerType) throws IOException;
}

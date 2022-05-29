package io.gnelsimonyan.notesetl.writer.file;

import io.gnelsimonyan.notesetl.processors.parquet.NoteSchema;
import org.springframework.batch.item.ItemWriter;

public interface NoteSchemaToParquetWriter extends ItemWriter<NoteSchema> {
}

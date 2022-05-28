package io.gnelsimonyan.notesetl.processors.parquet;

import io.gnelsimonyan.notesetl.source.NoteEntity;
import org.springframework.batch.item.ItemProcessor;

public interface NoteEntityToParquetProcessor extends ItemProcessor<NoteEntity, String> {
}

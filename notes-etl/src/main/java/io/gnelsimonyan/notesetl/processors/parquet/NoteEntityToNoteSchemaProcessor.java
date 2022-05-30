package io.gnelsimonyan.notesetl.processors.parquet;

import io.gnelsimonyan.notesetl.source.NoteEntity;
import org.springframework.batch.item.ItemProcessor;

public interface NoteEntityToNoteSchemaProcessor extends ItemProcessor<NoteEntity, NoteSchema> {
}

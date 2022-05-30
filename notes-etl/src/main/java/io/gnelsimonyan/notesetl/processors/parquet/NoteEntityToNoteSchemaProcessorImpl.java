package io.gnelsimonyan.notesetl.processors.parquet;

import io.gnelsimonyan.notesetl.source.NoteEntity;
import org.springframework.stereotype.Component;

@Component
public class NoteEntityToNoteSchemaProcessorImpl implements NoteEntityToNoteSchemaProcessor {

    @Override
    public NoteSchema process(NoteEntity noteEntity) {
        String updatedAt = null;

        if (noteEntity.updatedAt() != null) {
            updatedAt = noteEntity.updatedAt().toString();
        }

        return NoteSchema.of(
                noteEntity.id(),
                noteEntity.userId(),
                noteEntity.title(),
                noteEntity.text(),
                noteEntity
                        .createdAt()
                        .toString(),
                updatedAt
        );
    }
}

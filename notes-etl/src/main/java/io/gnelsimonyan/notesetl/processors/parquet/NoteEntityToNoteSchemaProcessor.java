package io.gnelsimonyan.notesetl.processors.parquet;

import io.gnelsimonyan.notesetl.source.NoteEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class NoteEntityToNoteSchemaProcessor implements ItemProcessor<NoteEntity, NoteSchema> {

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

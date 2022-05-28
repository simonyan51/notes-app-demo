package io.gnelsimonyan.notes.persistence;

import io.gnelsimonyan.notes.Note;

interface NoteEntityMapper {
    static Note mapNoteEntityToNote(final NoteEntity entity) {
        return Note.of(
                entity.id(),
                entity.userId(),
                entity.title(),
                entity.text(),
                entity.createdAt(),
                entity.updatedAt()
        );
    }

    static NoteEntity mapNoteToNoteEntity(Note note) {
        return NoteEntity.of(
                note.id(),
                note.title(),
                note.text(),
                note.userId(),
                note.createdAt(),
                note.updatedAt()
        );
    }
}

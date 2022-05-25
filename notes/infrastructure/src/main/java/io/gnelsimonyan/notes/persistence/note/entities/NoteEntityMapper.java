/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 19:40
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.persistence.note.entities;

import io.gnelsimonyan.notes.note.Note;
import io.gnelsimonyan.notes.persistence.note.entities.NoteEntity;

public interface NoteEntityMapper {
    static Note mapNoteEntityToNote(final NoteEntity entity) {
        return Note.of(
                entity.id(),
                entity.user().id(),
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
                note.title(),
                note.createdAt(),
                note.updatedAt(),
                note.userId()
        );
    }
}

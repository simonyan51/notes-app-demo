package io.gnelsimonyan.notes.boundaries.input;

import io.gnelsimonyan.notes.boundaries.input.params.SaveUserNoteParams;
import io.gnelsimonyan.notes.note.Note;

public interface UpdateUserNoteInputBoundary {
    Note updateUserNote(Long noteId, SaveUserNoteParams saveUserNoteParams);
}
package io.gnelsimonyan.notes.boundaries.input;

import io.gnelsimonyan.notes.note.Note;
import io.gnelsimonyan.notes.boundaries.input.params.SaveUserNoteParams;

public interface CreateUserNoteInputBoundary {
    Note createUserNote(SaveUserNoteParams createNoteParams);
}

package io.gnelsimonyan.notes.boundaries.input;

import io.gnelsimonyan.notes.boundaries.input.params.SaveUserNoteParams;
import io.gnelsimonyan.notes.Note;
import io.gnelsimonyan.notes.exceptions.InvalidParameterException;
import io.gnelsimonyan.notes.exceptions.NoteNotFoundException;

public interface UpdateUserNoteInputBoundary {
    Note updateUserNote(Long noteId, SaveUserNoteParams saveUserNoteParams) throws NoteNotFoundException, InvalidParameterException;
}

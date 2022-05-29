package io.gnelsimonyan.notes.boundaries.input;

import io.gnelsimonyan.notes.Note;
import io.gnelsimonyan.notes.boundaries.input.params.SaveUserNoteParams;
import io.gnelsimonyan.notes.exceptions.InvalidParameterException;

public interface CreateUserNoteInputBoundary {
    Note createUserNote(SaveUserNoteParams createNoteParams) throws InvalidParameterException;
}

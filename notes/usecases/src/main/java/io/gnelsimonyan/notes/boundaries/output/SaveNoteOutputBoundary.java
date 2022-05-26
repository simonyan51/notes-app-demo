package io.gnelsimonyan.notes.boundaries.output;

import io.gnelsimonyan.notes.Note;

public interface SaveNoteOutputBoundary {
    Note saveNote(Note noteToBeCreated);
}

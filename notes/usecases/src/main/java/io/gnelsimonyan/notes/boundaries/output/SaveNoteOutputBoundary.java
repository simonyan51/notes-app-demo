package io.gnelsimonyan.notes.boundaries.output;

import io.gnelsimonyan.notes.note.Note;

public interface SaveNoteOutputBoundary {
    Note saveNote(Note noteToBeCreated);
}

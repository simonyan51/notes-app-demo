package io.gnelsimonyan.notes.boundaries.input;

import io.gnelsimonyan.notes.exceptions.NoteNotFoundException;

public interface RemoveUserNoteInputBoundary {
    void removeUserNote(Long noteId, Long userId) throws NoteNotFoundException;
}

package io.gnelsimonyan.notes.boundaries.output;

import io.gnelsimonyan.notes.note.Note;

public interface FindUserNoteOutputBoundary {
    Note findUserNote(Long noteId, Long userId);
}

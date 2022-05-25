package io.gnelsimonyan.notes.boundaries.output;

import io.gnelsimonyan.notes.note.Note;

import java.util.List;

public interface FindUserNoteOutputBoundary {
    List<Note> findNoteByUserId(Long userId);

    Note findUserNote(Long noteId, Long userId);
}

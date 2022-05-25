package io.gnelsimonyan.notes.boundaries.input;

import io.gnelsimonyan.notes.note.Note;

import java.util.List;

public interface FindUserNoteInputBoundary {
    List<Note> findUserNotes(Long userId);

    Note findUserNote(Long noteId, Long userId);
}
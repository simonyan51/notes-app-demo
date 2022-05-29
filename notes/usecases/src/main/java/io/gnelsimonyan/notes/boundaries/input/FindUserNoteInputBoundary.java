package io.gnelsimonyan.notes.boundaries.input;

import io.gnelsimonyan.notes.Note;
import io.gnelsimonyan.notes.exceptions.NoteNotFoundException;

import java.util.List;

public interface FindUserNoteInputBoundary {
    List<Note> findUserNotes(Long userId);

    Note findUserNote(Long noteId, Long userId) throws NoteNotFoundException;
}

package io.gnelsimonyan.notes.boundaries.input;

public interface RemoveUserNoteInputBoundary {
    void removeUserNote(Long noteId, Long userId);
}

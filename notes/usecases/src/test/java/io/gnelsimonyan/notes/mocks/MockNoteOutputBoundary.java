package io.gnelsimonyan.notes.mocks;

import io.gnelsimonyan.notes.Note;
import io.gnelsimonyan.notes.boundaries.output.FindUserNoteOutputBoundary;
import io.gnelsimonyan.notes.boundaries.output.SaveNoteOutputBoundary;

import java.time.LocalDateTime;
import java.util.List;

public class MockNoteOutputBoundary implements SaveNoteOutputBoundary, FindUserNoteOutputBoundary {
    @Override
    public Note saveNote(Note noteToBeCreated) {
        return Note.of(
                1L,
                noteToBeCreated.userId(),
                noteToBeCreated.title(),
                noteToBeCreated.text(),
                LocalDateTime.now(),
                null
        );
    }

    @Override
    public List<Note> findNoteByUserId(Long userId) {
        return List.of(createMockNote(userId, null));
    }

    @Override
    public Note findUserNote(Long noteId, Long userId) {
        return createMockNote(userId, noteId);
    }

    private Note createMockNote(Long userId, Long noteId) {
        return Note.of(
                noteId == null ? 1L : noteId,
                userId,
                "title",
                "text",
                LocalDateTime.now(),
                null
        );
    }
}

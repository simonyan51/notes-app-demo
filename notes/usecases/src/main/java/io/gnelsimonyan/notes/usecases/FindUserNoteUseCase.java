package io.gnelsimonyan.notes.usecases;

import io.gnelsimonyan.notes.boundaries.input.FindUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.output.FindUserNoteOutputBoundary;
import io.gnelsimonyan.notes.common.Assert;
import io.gnelsimonyan.notes.Note;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindUserNoteUseCase implements FindUserNoteInputBoundary {

    private final FindUserNoteOutputBoundary findUserNoteOutputBoundary;

    @Override
    public List<Note> findUserNotes(final Long userId) {
        Assert.notNull(userId, "userId must be provided");

        return findUserNoteOutputBoundary.findNoteByUserId(userId);
    }

    @Override
    public Note findUserNote(final Long noteId, final Long userId) {
        Assert.notNull(noteId, "noteId must be provided");
        Assert.notNull(userId, "userId must be provided");

        Note note = findUserNoteOutputBoundary.findUserNote(noteId, userId);

        if (note == null) {
            throw new IllegalArgumentException("Note does not found");
        }

        return note;
    }
}

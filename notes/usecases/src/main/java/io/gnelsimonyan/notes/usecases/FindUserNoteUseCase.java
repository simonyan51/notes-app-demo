package io.gnelsimonyan.notes.usecases;

import io.gnelsimonyan.notes.boundaries.input.FindUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.output.FindUserNoteOutputBoundary;
import io.gnelsimonyan.notes.common.Assert;
import io.gnelsimonyan.notes.Note;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@AllArgsConstructor
public class FindUserNoteUseCase implements FindUserNoteInputBoundary {
    private final Logger logger = LoggerFactory.getLogger(FindUserNoteUseCase.class);

    private final FindUserNoteOutputBoundary findUserNoteOutputBoundary;

    @Override
    public List<Note> findUserNotes(final Long userId) {
        Assert.notNull(userId, "userId must be provided");
        logger.trace("Fetching user-{} notes", userId);

        List<Note> notes = findUserNoteOutputBoundary.findNoteByUserId(userId);

        logger.debug("Successfully fetched user-{} notes", userId);
        return notes;
    }

    @Override
    public Note findUserNote(final Long noteId, final Long userId) {
        Assert.notNull(noteId, "noteId must be provided");
        Assert.notNull(userId, "userId must be provided");
        logger.trace("Fetching user-{} note by id {}", userId, noteId);

        Note note = findUserNoteOutputBoundary.findUserNote(noteId, userId);

        if (note == null) {
            throw new IllegalArgumentException("Note does not found");
        }

        logger.debug("Successfully fetched user-{} note by id {}", userId, noteId);
        return note;
    }
}

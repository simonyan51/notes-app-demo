package io.gnelsimonyan.notes.usecases;

import io.gnelsimonyan.notes.boundaries.input.RemoveUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.output.FindUserNoteOutputBoundary;
import io.gnelsimonyan.notes.boundaries.output.RemoveNoteOutputBoundary;
import io.gnelsimonyan.notes.common.Assert;
import io.gnelsimonyan.notes.Note;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public class RemoveUserNoteUseCase implements RemoveUserNoteInputBoundary {
    private final Logger logger = LoggerFactory.getLogger(RemoveUserNoteUseCase.class);

    private final RemoveNoteOutputBoundary removeNoteOutputBoundary;

    private final FindUserNoteOutputBoundary findUserNoteOutputBoundary;

    @Override
    public void removeUserNote(final Long noteId, final Long userId) {
        Assert.notNull(noteId, "noteId must be provided");
        Assert.notNull(userId, "userId must be provided");
        logger.trace("Removing user-{} note by id {}", userId, noteId);

        Note note = findUserNoteOutputBoundary.findUserNote(noteId, userId);

        if (note == null) {
            throw new IllegalArgumentException("Note does not exists");
        }

        removeNoteOutputBoundary.removeNote(noteId);

        logger.debug("Successfully removed user-{} note by id {}", userId, noteId);
    }
}

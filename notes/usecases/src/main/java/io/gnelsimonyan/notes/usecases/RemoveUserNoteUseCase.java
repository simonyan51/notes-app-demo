/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 14:16
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.usecases;

import io.gnelsimonyan.notes.boundaries.input.RemoveUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.output.FindUserNoteOutputBoundary;
import io.gnelsimonyan.notes.boundaries.output.RemoveNoteOutputBoundary;
import io.gnelsimonyan.notes.common.Assert;
import io.gnelsimonyan.notes.Note;

public class RemoveUserNoteUseCase implements RemoveUserNoteInputBoundary {

    private final RemoveNoteOutputBoundary removeNoteOutputBoundary;

    private final FindUserNoteOutputBoundary findUserNoteOutputBoundary;

    public RemoveUserNoteUseCase(
            final RemoveNoteOutputBoundary removeNoteOutputBoundary,
            final FindUserNoteOutputBoundary findUserNoteOutputBoundary
    ) {
        this.removeNoteOutputBoundary = removeNoteOutputBoundary;
        this.findUserNoteOutputBoundary = findUserNoteOutputBoundary;
    }

    @Override
    public void removeUserNote(final Long noteId, final Long userId) {
        Assert.notNull(noteId, "noteId must be provided");
        Assert.notNull(userId, "userId must be provided");

        Note note = findUserNoteOutputBoundary.findUserNote(noteId, userId);

        if (note == null) {
            throw new IllegalArgumentException("Note does not exists");
        }

        removeNoteOutputBoundary.removeNote(noteId);
    }
}

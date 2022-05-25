/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 13:01
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.usecases;

import io.gnelsimonyan.notes.boundaries.input.UpdateUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.params.SaveUserNoteParams;
import io.gnelsimonyan.notes.boundaries.output.FindUserNoteOutputBoundary;
import io.gnelsimonyan.notes.boundaries.output.SaveNoteOutputBoundary;
import io.gnelsimonyan.notes.boundaries.output.TransactionManagerOutputBoundary;
import io.gnelsimonyan.notes.common.Assert;
import io.gnelsimonyan.notes.note.Note;

public class UpdateUserNoteUseCase implements UpdateUserNoteInputBoundary {

    private final FindUserNoteOutputBoundary findUserNoteOutputBoundary;
    private final SaveNoteOutputBoundary saveNoteOutputBoundary;
    private final TransactionManagerOutputBoundary transactionManager;

    public UpdateUserNoteUseCase(
            final FindUserNoteOutputBoundary findUserNoteOutputBoundary,
            final SaveNoteOutputBoundary saveNoteOutputBoundary,
            final TransactionManagerOutputBoundary transactionManagerOutputBoundary
            ) {
        this.findUserNoteOutputBoundary = findUserNoteOutputBoundary;
        this.saveNoteOutputBoundary = saveNoteOutputBoundary;
        this.transactionManager = transactionManagerOutputBoundary;
    }

    @Override
    public Note updateUserNote(final Long noteId, final SaveUserNoteParams saveUserNoteParams) {
        Assert.notNull(noteId, "noteId must be provided");
        Assert.notNull(saveUserNoteParams, "saveNoteParams must be provided");

        saveUserNoteParams.validateParams();

        return transactionManager
                .execute(() -> updateUserNoteTransaction(noteId, saveUserNoteParams));
    }

    private Note updateUserNoteTransaction(final Long noteId, final SaveUserNoteParams saveUserNoteParams) {
        Note note = findUserNoteOutputBoundary.findUserNote(noteId, saveUserNoteParams.userId());

        if (note == null) {
            throw new IllegalArgumentException("Note does not exists");
        }

        note.changeTitle(saveUserNoteParams.title());
        note.changeText(saveUserNoteParams.text());

        return saveNoteOutputBoundary.saveNote(note);
    }
}

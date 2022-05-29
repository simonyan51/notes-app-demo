package io.gnelsimonyan.notes.usecases;

import io.gnelsimonyan.notes.boundaries.input.UpdateUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.params.SaveUserNoteParams;
import io.gnelsimonyan.notes.boundaries.output.FindUserNoteOutputBoundary;
import io.gnelsimonyan.notes.boundaries.output.SaveNoteOutputBoundary;
import io.gnelsimonyan.notes.boundaries.output.TransactionManagerOutputBoundary;
import io.gnelsimonyan.notes.common.Assert;
import io.gnelsimonyan.notes.Note;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public class UpdateUserNoteUseCase implements UpdateUserNoteInputBoundary {
    private final Logger logger = LoggerFactory.getLogger(UpdateUserNoteUseCase.class);

    private final FindUserNoteOutputBoundary findUserNoteOutputBoundary;
    private final SaveNoteOutputBoundary saveNoteOutputBoundary;
    private final TransactionManagerOutputBoundary transactionManager;

    @Override
    public Note updateUserNote(final Long noteId, final SaveUserNoteParams saveUserNoteParams) {
        Assert.notNull(noteId, "noteId must be provided");
        Assert.notNull(saveUserNoteParams, "saveNoteParams must be provided");

        saveUserNoteParams.validateParams();

        return transactionManager
                .execute(() -> updateUserNoteTransaction(noteId, saveUserNoteParams));
    }

    private Note updateUserNoteTransaction(final Long noteId, final SaveUserNoteParams saveUserNoteParams) {
        logger.trace("Updating note by id {} with params: {}", noteId, saveUserNoteParams);

        Note note = findUserNoteOutputBoundary.findUserNote(noteId, saveUserNoteParams.userId());

        if (note == null) {
            throw new IllegalArgumentException("Note does not exists");
        }

        note.changeTitle(saveUserNoteParams.title());
        note.changeText(saveUserNoteParams.text());

        note = saveNoteOutputBoundary.saveNote(note);

        logger.debug("Successfully saved note by id {} with params: {}",noteId, saveUserNoteParams);
        return note;
    }
}

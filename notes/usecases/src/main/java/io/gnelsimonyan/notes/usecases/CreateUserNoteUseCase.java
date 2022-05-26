package io.gnelsimonyan.notes.usecases;

import io.gnelsimonyan.notes.common.Assert;
import io.gnelsimonyan.notes.Note;
import io.gnelsimonyan.notes.user.User;
import io.gnelsimonyan.notes.boundaries.input.CreateUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.params.SaveUserNoteParams;
import io.gnelsimonyan.notes.boundaries.output.SaveNoteOutputBoundary;
import io.gnelsimonyan.notes.boundaries.output.FindUserOutputBoundary;

public class CreateUserNoteUseCase implements CreateUserNoteInputBoundary {

    private final FindUserOutputBoundary findUserOutputBoundary;

    private final SaveNoteOutputBoundary createNoteOutputBoundary;

    public CreateUserNoteUseCase(
            final FindUserOutputBoundary findUserOutputBoundary,
            final SaveNoteOutputBoundary createNoteOutputBoundary
    ) {
        this.findUserOutputBoundary = findUserOutputBoundary;
        this.createNoteOutputBoundary = createNoteOutputBoundary;
    }

    @Override
    public Note createUserNote(final SaveUserNoteParams createNoteParams) {
        Assert.notNull(createNoteParams, "saveNoteParams must be provided");

        createNoteParams.validateParams();
        ensureUserExists(createNoteParams.userId());

        return createNoteOutputBoundary.saveNote(
                Note.of(
                        createNoteParams.userId(),
                        createNoteParams.title(),
                        createNoteParams.text()
                )
        );
    }

    private void ensureUserExists(final Long userId) {
        User foundUser = findUserOutputBoundary.findUserById(userId);

        if (foundUser == null) {
            throw new IllegalArgumentException("User with provided userId does not exists");
        }
    }
}

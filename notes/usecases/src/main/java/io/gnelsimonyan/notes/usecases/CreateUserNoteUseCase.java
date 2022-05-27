package io.gnelsimonyan.notes.usecases;

import io.gnelsimonyan.notes.common.Assert;
import io.gnelsimonyan.notes.Note;
import io.gnelsimonyan.notes.boundaries.input.CreateUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.params.SaveUserNoteParams;
import io.gnelsimonyan.notes.boundaries.output.SaveNoteOutputBoundary;

public class CreateUserNoteUseCase implements CreateUserNoteInputBoundary {

    private final SaveNoteOutputBoundary createNoteOutputBoundary;

    public CreateUserNoteUseCase(
            final SaveNoteOutputBoundary createNoteOutputBoundary
    ) {
        this.createNoteOutputBoundary = createNoteOutputBoundary;
    }

    @Override
    public Note createUserNote(final SaveUserNoteParams createNoteParams) {
        Assert.notNull(createNoteParams, "saveNoteParams must be provided");

        createNoteParams.validateParams();

        return createNoteOutputBoundary.saveNote(
                Note.of(
                        createNoteParams.userId(),
                        createNoteParams.title(),
                        createNoteParams.text()
                )
        );
    }
}

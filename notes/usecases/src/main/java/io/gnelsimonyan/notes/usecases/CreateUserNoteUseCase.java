package io.gnelsimonyan.notes.usecases;

import io.gnelsimonyan.notes.common.Assert;
import io.gnelsimonyan.notes.Note;
import io.gnelsimonyan.notes.boundaries.input.CreateUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.params.SaveUserNoteParams;
import io.gnelsimonyan.notes.boundaries.output.SaveNoteOutputBoundary;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public class CreateUserNoteUseCase implements CreateUserNoteInputBoundary {
    private final Logger logger = LoggerFactory.getLogger(CreateUserNoteUseCase.class);

    private final SaveNoteOutputBoundary createNoteOutputBoundary;

    @Override
    public Note createUserNote(final SaveUserNoteParams createNoteParams) {
        Assert.notNull(createNoteParams, "saveNoteParams must be provided");
        createNoteParams.validateParams();
        logger.trace("Creating note with params: {}", createNoteParams);


        Note note = createNoteOutputBoundary.saveNote(
                Note.of(
                        createNoteParams.userId(),
                        createNoteParams.title(),
                        createNoteParams.text()
                )
        );

        logger.debug("Successfully saved note with params: {}", createNoteParams);
        return note;
    }
}

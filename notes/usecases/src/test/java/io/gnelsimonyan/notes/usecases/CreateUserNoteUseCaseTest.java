package io.gnelsimonyan.notes.usecases;

import io.gnelsimonyan.notes.Note;
import io.gnelsimonyan.notes.boundaries.input.CreateUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.params.SaveUserNoteParams;
import io.gnelsimonyan.notes.boundaries.output.SaveNoteOutputBoundary;
import io.gnelsimonyan.notes.exceptions.InvalidParameterException;
import io.gnelsimonyan.notes.mocks.MockNoteOutputBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserNoteUseCaseTest {

    CreateUserNoteInputBoundary createUserNoteUseCase;

    SaveNoteOutputBoundary saveNoteOutputBoundary;

    @BeforeEach
    void beforeEachTest() {
        saveNoteOutputBoundary = new MockNoteOutputBoundary();
        createUserNoteUseCase = new CreateUserNoteUseCase(saveNoteOutputBoundary);
    }

    @Test
    void whenCreatingNoteWithValidParameters_thenShouldBeCreatedSuccessfully() throws InvalidParameterException {
        SaveUserNoteParams params = SaveUserNoteParams.of(1L, "title", "text");

        Note createdNote = createUserNoteUseCase.createUserNote(params);

        assertNotNull(createdNote);
        assertNotNull(createdNote.id());
        assertNotNull(createdNote.createdAt());
        assertNull(createdNote.updatedAt());

    }

    @Test
    void whenCreatingNoteWithInvalidParameters_thenThrowInvalidParameterException() {
        SaveUserNoteParams params = SaveUserNoteParams.of(1L, "", "text");

        try {
            createUserNoteUseCase.createUserNote(params);
        } catch (InvalidParameterException exception) {
            assertNotNull(exception);
        }
    }
}

package io.gnelsimonyan.notes;

import io.gnelsimonyan.notes.boundaries.input.CreateUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.FindUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.RemoveUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.UpdateUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.output.*;
import io.gnelsimonyan.notes.usecases.CreateUserNoteUseCase;
import io.gnelsimonyan.notes.usecases.FindUserNoteUseCase;
import io.gnelsimonyan.notes.usecases.RemoveUserNoteUseCase;
import io.gnelsimonyan.notes.usecases.UpdateUserNoteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoteBoundariesConfiguration {
    @Bean
    public FindUserNoteInputBoundary findUserNoteInputBoundary(final FindUserNoteOutputBoundary findUserNoteOutputBoundary) {
        return new FindUserNoteUseCase(findUserNoteOutputBoundary);
    }

    @Bean
    public CreateUserNoteInputBoundary createUserNoteInputBoundary(final SaveNoteOutputBoundary saveNoteOutputBoundary) {
        return new CreateUserNoteUseCase(saveNoteOutputBoundary);
    }

    @Bean
    public UpdateUserNoteInputBoundary updateUserNoteInputBoundary(
            final FindUserNoteOutputBoundary findUserNoteOutputBoundary,
            final SaveNoteOutputBoundary saveNoteOutputBoundary,
            final TransactionManagerOutputBoundary transactionManagerOutputBoundary
    ) {
        return new UpdateUserNoteUseCase(
                findUserNoteOutputBoundary,
                saveNoteOutputBoundary,
                transactionManagerOutputBoundary
        );
    }

    @Bean
    public RemoveUserNoteInputBoundary removeUserNoteInputBoundary(
            final RemoveNoteOutputBoundary removeNoteOutputBoundary,
            final FindUserNoteOutputBoundary findUserNoteOutputBoundary
    ) {
        return new RemoveUserNoteUseCase(
                removeNoteOutputBoundary,
                findUserNoteOutputBoundary
        );
    }
}

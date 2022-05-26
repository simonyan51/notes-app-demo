package io.gnelsimonyan.notes;

import io.gnelsimonyan.notes.configurations.InfrastructureConfiguration;
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
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        InfrastructureConfiguration.class
})
public class NoteBoundariesConfiguration {
    @Bean
    FindUserNoteInputBoundary findUserNoteInputBoundary(final FindUserNoteOutputBoundary findUserNoteOutputBoundary) {
        return new FindUserNoteUseCase(findUserNoteOutputBoundary);
    }

    @Bean
    CreateUserNoteInputBoundary createUserNoteInputBoundary(
            final FindUserOutputBoundary findUserOutputBoundary,
            final SaveNoteOutputBoundary saveNoteOutputBoundary
    ) {
        return new CreateUserNoteUseCase(findUserOutputBoundary, saveNoteOutputBoundary);
    }

    @Bean
    UpdateUserNoteInputBoundary updateUserNoteInputBoundary(
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
    RemoveUserNoteInputBoundary removeUserNoteInputBoundary(
            final RemoveNoteOutputBoundary removeNoteOutputBoundary,
            final FindUserNoteOutputBoundary findUserNoteOutputBoundary
    ) {
        return new RemoveUserNoteUseCase(
                removeNoteOutputBoundary,
                findUserNoteOutputBoundary
        );
    }
}

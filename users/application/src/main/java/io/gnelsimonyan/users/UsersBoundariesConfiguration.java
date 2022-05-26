package io.gnelsimonyan.users;

import io.gnelsimonyan.users.boundaries.input.FindUserInputBoundary;
import io.gnelsimonyan.users.boundaries.output.FindUserOutputBoundary;
import io.gnelsimonyan.users.configuration.InfrastructureConfiguration;
import io.gnelsimonyan.users.usecases.FindUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(InfrastructureConfiguration.class)
public class UsersBoundariesConfiguration {
    @Bean
    FindUserInputBoundary findUserInputBoundary(FindUserOutputBoundary findUserOutputBoundary) {
        return new FindUserUseCase(findUserOutputBoundary);
    }
}

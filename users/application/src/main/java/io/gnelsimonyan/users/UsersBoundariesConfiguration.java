package io.gnelsimonyan.users;

import io.gnelsimonyan.users.boundaries.input.FindUserInputBoundary;
import io.gnelsimonyan.users.boundaries.input.SignInUserInputBoundary;
import io.gnelsimonyan.users.boundaries.output.FindUserOutputBoundary;
import io.gnelsimonyan.users.boundaries.output.SignInUserOutputBoundary;
import io.gnelsimonyan.users.common.TokenManager;
import io.gnelsimonyan.users.usecases.FindUserUseCase;
import io.gnelsimonyan.users.usecases.SignInUserUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(InfrastructureConfiguration.class)
public class UsersBoundariesConfiguration {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.subject}")
    private String jwtSubject;

    @Bean
    public FindUserInputBoundary findUserInputBoundary(FindUserOutputBoundary findUserOutputBoundary) {
        return new FindUserUseCase(findUserOutputBoundary);
    }

    @Bean
    public SignInUserInputBoundary signInUserInputBoundary(
            SignInUserOutputBoundary signInUserOutputBoundary,
            TokenManager tokenManager
    ) {
        return new SignInUserUseCase(
                signInUserOutputBoundary,
                tokenManager
        );
    }

    @Bean
    public TokenManager tokenManager() {
        return new TokenManager(jwtSubject, jwtSecret);
    }
}

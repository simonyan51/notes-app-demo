package io.gnelsimonyan.users.usecases;

import io.gnelsimonyan.users.boundaries.output.SignInUserOutputBoundary;
import io.gnelsimonyan.users.common.TokenManager;
import io.gnelsimonyan.users.user.User;
import io.gnelsimonyan.users.boundaries.input.SignInUserInputBoundary;
import io.gnelsimonyan.users.boundaries.input.params.SignInUserParams;
import io.gnelsimonyan.users.common.Assert;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public class SignInUserUseCase implements SignInUserInputBoundary {
    private final Logger logger = LoggerFactory.getLogger(FindUserUseCase.class);

    private final SignInUserOutputBoundary signInUserOutputBoundary;

    private final TokenManager tokenManager;

    @Override
    public String signIn(final SignInUserParams params) {
        Assert.notNull(params, "Sign In params must be provided");
        logger.trace("Signing up user by email {}", params.email());

        User foundUser = signInUserOutputBoundary.authenticate(
                params.email(),
                params.password()
        );

        logger.trace("Generating token for user {}", params.email());

        String token = tokenManager.generateToken(foundUser.email());

        logger.debug("User by email {} signed up successfully", params.email());
        return token;
    }
}

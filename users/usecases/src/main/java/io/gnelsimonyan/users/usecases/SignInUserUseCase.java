package io.gnelsimonyan.users.usecases;

import io.gnelsimonyan.users.boundaries.output.SignInUserOutputBoundary;
import io.gnelsimonyan.users.common.TokenManager;
import io.gnelsimonyan.users.user.User;
import io.gnelsimonyan.users.boundaries.input.SignInUserInputBoundary;
import io.gnelsimonyan.users.boundaries.input.params.SignInUserParams;
import io.gnelsimonyan.users.common.Assert;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SignInUserUseCase implements SignInUserInputBoundary {

    private final SignInUserOutputBoundary signInUserOutputBoundary;

    private final TokenManager tokenManager;

    @Override
    public String signIn(final SignInUserParams params) {
        Assert.notNull(params, "Sign In params must be provided");

        User foundUser = signInUserOutputBoundary.authenticate(
                params.email(),
                params.password()
        );

        return tokenManager.generateToken(foundUser.email());
    }
}

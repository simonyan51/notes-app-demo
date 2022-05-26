package io.gnelsimonyan.users.usecases;

import io.gnelsimonyan.users.boundaries.output.SignInUserOutputBoundary;
import io.gnelsimonyan.users.common.JWTUtils;
import io.gnelsimonyan.users.user.User;
import io.gnelsimonyan.users.boundaries.input.SignInUserInputBoundary;
import io.gnelsimonyan.users.boundaries.input.params.SignInUserParams;
import io.gnelsimonyan.users.common.Assert;

public class SignInUserUseCase implements SignInUserInputBoundary {

    private final SignInUserOutputBoundary signInUserOutputBoundary;

    private final JWTUtils jwtUtils;

    public SignInUserUseCase(
            final SignInUserOutputBoundary signInUserOutputBoundary,
            final JWTUtils jwtUtils
    ) {
        this.signInUserOutputBoundary = signInUserOutputBoundary;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public String signIn(final SignInUserParams params) {
        Assert.notNull(params, "Sign In params must be provided");

        User foundUser = signInUserOutputBoundary.authenticate(
                params.email(),
                params.password()
        );

        return jwtUtils.generateToken(foundUser.email());
    }
}

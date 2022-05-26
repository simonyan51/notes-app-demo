package io.gnelsimonyan.users.usecases;

import io.gnelsimonyan.users.User;
import io.gnelsimonyan.users.boundaries.input.SignInUserInputBoundary;
import io.gnelsimonyan.users.boundaries.input.params.SignInUserParams;
import io.gnelsimonyan.users.boundaries.output.SignInOutputBoundary;

public class SignInUserUseCase implements SignInUserInputBoundary {

    private final SignInOutputBoundary signInOutputBoundary;

    public SignInUserUseCase(final SignInOutputBoundary signInOutputBoundary) {
        this.signInOutputBoundary = signInOutputBoundary;
    }

    @Override
    public User signIn(final SignInUserParams params) {
        User foundUser = signInOutputBoundary.findUserByEmail(params.email());

        if (!foundUser.validatePassword(params.password())) throw new IllegalArgumentException("Invalid username or password");

        return foundUser;
    }
}

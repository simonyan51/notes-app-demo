package io.gnelsimonyan.users.boundaries.input;

import io.gnelsimonyan.users.boundaries.input.params.SignInUserParams;
import io.gnelsimonyan.users.exceptions.UserNotFoundException;

public interface SignInUserInputBoundary {
    String signIn(SignInUserParams params) throws UserNotFoundException;
}

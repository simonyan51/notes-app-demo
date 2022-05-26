package io.gnelsimonyan.users.boundaries.input;

import io.gnelsimonyan.users.User;
import io.gnelsimonyan.users.boundaries.input.params.SignInUserParams;

public interface SignInUserInputBoundary {
    User signIn(SignInUserParams params);
}

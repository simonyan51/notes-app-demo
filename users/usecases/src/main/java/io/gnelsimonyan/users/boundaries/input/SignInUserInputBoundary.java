package io.gnelsimonyan.users.boundaries.input;

import io.gnelsimonyan.users.boundaries.input.params.SignInUserParams;

public interface SignInUserInputBoundary {
    String signIn(SignInUserParams params);
}

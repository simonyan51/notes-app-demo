package io.gnelsimonyan.notes.boundaries.input;

import io.gnelsimonyan.notes.boundaries.input.params.SignInUserParams;

public interface SignInUserInputBoundary {

    boolean signIn(SignInUserParams signInUserParams);
}

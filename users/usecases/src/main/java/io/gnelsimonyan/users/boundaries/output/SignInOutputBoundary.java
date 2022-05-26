package io.gnelsimonyan.users.boundaries.output;

import io.gnelsimonyan.users.User;

public interface SignInOutputBoundary {
    User findUserByEmail(String email);
}

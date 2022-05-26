package io.gnelsimonyan.users.boundaries.input;

import io.gnelsimonyan.users.User;

public interface FindUserInputBoundary {
    User findUserByEmail(String email);
}

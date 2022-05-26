package io.gnelsimonyan.users.boundaries.output;

import io.gnelsimonyan.users.User;

public interface FindUserOutputBoundary {
    User findUserByEmail(String email);
}

package io.gnelsimonyan.users.boundaries.output;

import io.gnelsimonyan.users.user.User;

public interface FindUserOutputBoundary {
    User findUserByEmail(String email);
}

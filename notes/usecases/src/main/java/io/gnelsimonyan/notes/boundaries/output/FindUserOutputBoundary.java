package io.gnelsimonyan.notes.boundaries.output;

import io.gnelsimonyan.notes.user.User;

public interface FindUserOutputBoundary {
    User findUserById(Long id);

    User findUserByEmail(String email);
}

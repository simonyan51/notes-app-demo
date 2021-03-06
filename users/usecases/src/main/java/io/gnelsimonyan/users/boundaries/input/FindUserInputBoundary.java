package io.gnelsimonyan.users.boundaries.input;

import io.gnelsimonyan.users.exceptions.UserNotFoundException;
import io.gnelsimonyan.users.user.User;

public interface FindUserInputBoundary {
    User findUserByEmail(String email) throws UserNotFoundException;
}

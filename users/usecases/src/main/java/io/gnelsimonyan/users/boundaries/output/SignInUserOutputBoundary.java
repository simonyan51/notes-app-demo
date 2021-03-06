package io.gnelsimonyan.users.boundaries.output;

import io.gnelsimonyan.users.exceptions.UserNotFoundException;
import io.gnelsimonyan.users.user.User;

public interface SignInUserOutputBoundary {
    User authenticate(String email, String password) throws UserNotFoundException;
}

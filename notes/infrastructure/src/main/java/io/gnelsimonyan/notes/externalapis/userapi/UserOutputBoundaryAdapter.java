package io.gnelsimonyan.notes.externalapis.userapi;

import io.gnelsimonyan.notes.boundaries.output.FindUserOutputBoundary;
import io.gnelsimonyan.notes.user.User;
import org.springframework.stereotype.Component;

@Component
class UserOutputBoundaryAdapter implements FindUserOutputBoundary {
    @Override
    public User findUserById(Long id) {
        return null;
    }
}

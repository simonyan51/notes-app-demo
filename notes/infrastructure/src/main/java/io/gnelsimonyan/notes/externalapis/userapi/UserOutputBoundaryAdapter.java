package io.gnelsimonyan.notes.externalapis.userapi;

import io.gnelsimonyan.notes.boundaries.output.FindUserOutputBoundary;
import io.gnelsimonyan.notes.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserOutputBoundaryAdapter implements FindUserOutputBoundary {
    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }
}

package io.gnelsimonyan.users.usecases;

import io.gnelsimonyan.users.User;
import io.gnelsimonyan.users.boundaries.input.FindUserInputBoundary;
import io.gnelsimonyan.users.boundaries.output.FindUserOutputBoundary;
import io.gnelsimonyan.users.common.Assert;

public class FindUserUseCase implements FindUserInputBoundary {

    private final FindUserOutputBoundary findUserOutputBoundary;

    public FindUserUseCase(final FindUserOutputBoundary findUserOutputBoundary) {
        this.findUserOutputBoundary = findUserOutputBoundary;
    }

    @Override
    public User findUserByEmail(final String email) {
        Assert.notEmpty(email, "Email must be provided");

        return findUserOutputBoundary.findUserByEmail(email);
    }
}

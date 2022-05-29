package io.gnelsimonyan.users.usecases;

import io.gnelsimonyan.users.user.User;
import io.gnelsimonyan.users.boundaries.input.FindUserInputBoundary;
import io.gnelsimonyan.users.boundaries.output.FindUserOutputBoundary;
import io.gnelsimonyan.users.common.Assert;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public class FindUserUseCase implements FindUserInputBoundary {
    private final Logger logger = LoggerFactory.getLogger(FindUserUseCase.class);

    private final FindUserOutputBoundary findUserOutputBoundary;

    @Override
    public User findUserByEmail(final String email) {
        Assert.notEmpty(email, "Email must be provided");
        logger.trace("Fetching user by email {}", email);

        User user = findUserOutputBoundary.findUserByEmail(email);

        if (user == null) throw new IllegalArgumentException("User not found");

        logger.debug("Successfully fetched user by email {}", email);
        return user;
    }
}

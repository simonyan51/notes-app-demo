package io.gnelsimonyan.users;

import io.gnelsimonyan.users.common.AbstractDomain;

import java.time.LocalDateTime;

public interface User extends AbstractDomain {
    String email();

    String password();

    boolean validatePassword(String password);

    static User of(final String email, final String password) {
        return new UserImpl(email, password);
    }

    static User of(
            final Long id,
            final String email,
            final String password,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        return new UserImpl(
                id,
                email,
                password,
                createdAt,
                updatedAt
        );
    }
}

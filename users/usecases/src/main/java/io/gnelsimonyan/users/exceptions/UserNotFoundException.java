package io.gnelsimonyan.users.exceptions;

import lombok.Getter;

@Getter
public class UserNotFoundException extends Exception {
    private String email;

    public UserNotFoundException(final String message) {
        super(message);
    }

    public UserNotFoundException(final String message, final String email) {
        super(message);
        this.email = email;
    }
}

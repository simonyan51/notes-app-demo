package io.gnelsimonyan.users.rest.endpoints.dtos;

import javax.validation.constraints.*;

public class SignInRequest {
    @NotEmpty
    @Email
    private final String email;

    @NotNull
    @Size(min = 8, message = "Password size must not exceed 8 characters")
    private final String password;

    public SignInRequest(
            final String email,
            final String password
    ) {
        this.email = email;
        this.password = password;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }
}

package io.gnelsimonyan.users.rest.endpoints.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class SignInRequest {
    @NotEmpty
    @Email
    private final String email;

    @NotEmpty
    @Min(8)
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

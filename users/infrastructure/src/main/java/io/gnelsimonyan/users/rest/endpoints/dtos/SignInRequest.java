package io.gnelsimonyan.users.rest.endpoints.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Data
@Accessors(fluent = true)
public class SignInRequest {
    @NotEmpty
    @Email
    private final String email;

    @NotNull
    @Size(min = 8, message = "Password size must not exceed 8 characters")
    private final String password;
}

package io.gnelsimonyan.users.boundaries.input.params;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@AllArgsConstructor(staticName = "of")
public class SignInUserParams {

    private final String email;

    private final String password;
}


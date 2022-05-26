package io.gnelsimonyan.users.boundaries.input.params;

public interface SignInUserParams {

    String email();

    String password();

    static SignInUserParams of(final String email, final String password) {
        return new SignInUserParamsImpl(email, password);
    }

    record SignInUserParamsImpl(String email, String password) implements SignInUserParams { }
}


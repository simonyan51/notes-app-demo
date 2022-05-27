package io.gnelsimonyan.users.apiclientmodels;

public interface SignInParamsRequest {
    String email();

    String password();

    static SignInParamsRequest of(
            String email,
            String password
    ) {
        return new SignInParamsRequestImpl(email, password);
    }

    record SignInParamsRequestImpl(String email, String password) implements SignInParamsRequest {}
}

package io.gnelsimonyan.users.auth;

import io.gnelsimonyan.users.boundaries.input.FindUserInputBoundary;
import io.gnelsimonyan.users.boundaries.output.SignInUserOutputBoundary;
import io.gnelsimonyan.users.user.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
class AuthenticateUserAdapter implements SignInUserOutputBoundary {

    private final AuthenticationManager authenticationManager;

    private final FindUserInputBoundary findUserInputBoundary;

    public AuthenticateUserAdapter(
            final AuthenticationManager authenticationManager,
            final FindUserInputBoundary findUserInputBoundary
            ) {
        this.authenticationManager = authenticationManager;
        this.findUserInputBoundary = findUserInputBoundary;
    }

    @Override
    public User authenticate(String email, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            return findUserInputBoundary.findUserByEmail(email);

        } catch (BadCredentialsException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}

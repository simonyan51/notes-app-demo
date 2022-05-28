package io.gnelsimonyan.users.security;

import io.gnelsimonyan.users.boundaries.input.FindUserInputBoundary;
import io.gnelsimonyan.users.boundaries.output.SignInUserOutputBoundary;
import io.gnelsimonyan.users.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
class AuthenticateUserAdapter implements SignInUserOutputBoundary {

    private final AuthenticationManager authenticationManager;

    private final FindUserInputBoundary findUserInputBoundary;

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

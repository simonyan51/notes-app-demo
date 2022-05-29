package io.gnelsimonyan.users.security;

import io.gnelsimonyan.users.boundaries.input.FindUserInputBoundary;
import io.gnelsimonyan.users.exceptions.UserNotFoundException;
import io.gnelsimonyan.users.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class UserDetailsServiceAdapter implements UserDetailsService {
    private final FindUserInputBoundary findUserInputBoundary;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = findUserInputBoundary.findUserByEmail(username);

            return AuthenticatedUserBuilder
                    .create()
                    .email(user.email())
                    .password(user.password())
                    .build();

        } catch (UserNotFoundException exception) {

            exception.printStackTrace();
            throw new UsernameNotFoundException("Username not found");

        } catch (UsernameNotFoundException exception) {

            exception.printStackTrace();
            throw exception;

        }
    }
}

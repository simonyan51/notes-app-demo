package io.gnelsimonyan.users.security;

import io.gnelsimonyan.users.boundaries.input.FindUserInputBoundary;
import io.gnelsimonyan.users.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
class UserDetailsServiceAdapter implements UserDetailsService {
    private final FindUserInputBoundary findUserInputBoundary;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserInputBoundary.findUserByEmail(username);

        return AuthenticatedUserBuilder
                .create()
                .email(user.email())
                .password(user.password())
                .build();
    }
}

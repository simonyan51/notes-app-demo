package io.gnelsimonyan.users.auth;

import io.gnelsimonyan.users.boundaries.input.FindUserInputBoundary;
import io.gnelsimonyan.users.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserDetailsServiceAdapter implements UserDetailsService {
    private final FindUserInputBoundary findUserInputBoundary;

    public UserDetailsServiceAdapter(FindUserInputBoundary findUserInputBoundary) {
        this.findUserInputBoundary = findUserInputBoundary;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserInputBoundary.findUserByEmail(username);

        return new org.springframework.security.core.userdetails.User(
                user.email(),
                user.password(),
                Collections.emptyList()
        );
    }
}

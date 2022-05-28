package io.gnelsimonyan.users.security;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor(staticName = "create")
public class AuthenticatedUserBuilder {
    private String email;

    private String password;

    private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();

    public AuthenticatedUserBuilder email(String email) {
        this.email = email;

        return this;
    }

    public AuthenticatedUserBuilder password(String password) {
        this.password = password;

        return this;
    }

    public AuthenticatedUserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;

        return this;
    }

    public UserDetails build() {
        return new User(
                email,
                password,
                authorities
        );
    }
}

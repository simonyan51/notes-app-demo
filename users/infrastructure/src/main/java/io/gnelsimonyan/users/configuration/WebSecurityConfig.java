package io.gnelsimonyan.users.configuration;

import io.gnelsimonyan.users.auth.UserDetailsServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceAdapter userDetailsServiceAdapter;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().authenticated()
                )
                .userDetailsService(userDetailsServiceAdapter)
                .cors()
                .and()
                .formLogin()
                .disable()
                .build();
    }
}

package io.gnelsimonyan.users.configuration;

import io.gnelsimonyan.users.auth.JwtAuthorizationFilter;
import io.gnelsimonyan.users.auth.UserDetailsServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailsServiceAdapter userDetailsServiceAdapter;

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain (HttpSecurity http) throws Exception {
        return http
                .csrf()
                .disable()
                .httpBasic()
                .disable()
                .cors()
                .and()
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/v1/auth/sign-in")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .userDetailsService(userDetailsServiceAdapter)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {;
        return configuration.getAuthenticationManager();
    }

}

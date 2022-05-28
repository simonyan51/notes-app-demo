package io.gnelsimonyan.notes.security;

import io.gnelsimonyan.notes.externalapis.userapi.UserApiClient;
import io.gnelsimonyan.notes.externalapis.userapi.AuthorizedUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
class AuthorizationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final UserApiClient userApiClient;

    AuthorizationFilter(UserApiClient userApiClient) {
        this.userApiClient = userApiClient;
    }

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {
        String authorizationToken = request.getHeader(AUTHORIZATION_HEADER);

        if (authorizationToken != null && !authorizationToken.isBlank()) {
            try {
                AuthorizedUser userResponse = userApiClient.getAuthorizedUser(authorizationToken);

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userResponse,
                        null,
                        Collections.emptyList()
                );

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            } catch (HttpClientErrorException.Forbidden forbiddenException) {
                SecurityContextHolder.getContext().setAuthentication(null);
            }
        }

        filterChain.doFilter(request, response);

    }
}

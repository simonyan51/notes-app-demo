package io.gnelsimonyan.users.auth;

import com.auth0.jwt.exceptions.JWTVerificationException;
import io.gnelsimonyan.users.common.TokenManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_PREFIX = "Bearer ";

    private final UserDetailsServiceAdapter userDetailsService;

    private final TokenManager tokenManager;

    public JwtAuthorizationFilter(
            final UserDetailsServiceAdapter userDetailsService,
            final TokenManager tokenManager
    ) {
        this.userDetailsService = userDetailsService;
        this.tokenManager = tokenManager;
    }

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(JwtAuthorizationFilter.AUTHORIZATION_HEADER);

        if (authHeader != null
                        && !authHeader.isBlank()
                        && authHeader.startsWith(JwtAuthorizationFilter.BEARER_TOKEN_PREFIX)) {

            String jwt = authHeader.replace(JwtAuthorizationFilter.BEARER_TOKEN_PREFIX, "");

            if (jwt.isBlank()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
                return;
            }

            try {
                String email = tokenManager.validateTokenAndRetrieveSubject(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                userDetails.getPassword(),
                                userDetails.getAuthorities()
                        );

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            } catch (JWTVerificationException exc) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
                return;
            }

        }

        filterChain.doFilter(request, response);
    }
}

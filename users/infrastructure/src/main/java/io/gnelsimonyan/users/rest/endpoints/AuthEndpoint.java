package io.gnelsimonyan.users.rest.endpoints;

import io.gnelsimonyan.users.boundaries.input.FindUserInputBoundary;
import io.gnelsimonyan.users.boundaries.input.SignInUserInputBoundary;
import io.gnelsimonyan.users.exceptions.UserNotFoundException;
import io.gnelsimonyan.users.rest.endpoints.dtos.JwtTokenResponse;
import io.gnelsimonyan.users.rest.endpoints.dtos.SignInRequest;
import io.gnelsimonyan.users.rest.endpoints.dtos.UserResponse;
import io.gnelsimonyan.users.rest.mappers.UserDtoMapper;
import io.gnelsimonyan.users.user.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/auth")
@AllArgsConstructor
public class AuthEndpoint {
    private final Logger logger = LoggerFactory.getLogger(AuthEndpoint.class);

    private final SignInUserInputBoundary signInUserInputBoundary;

    private final FindUserInputBoundary findUserInputBoundary;

    @PostMapping("sign-in")
    public ResponseEntity<JwtTokenResponse> signIn(@RequestBody @Valid SignInRequest signInParamsRequest) throws UserNotFoundException {
        logger.info("Sign up user by email {}", signInParamsRequest.email());

        String accessToken = signInUserInputBoundary.signIn(
                UserDtoMapper.mapSignInUserParamsRequestToSignInUserParams(signInParamsRequest)
        );

        logger.debug("User with email {} signed up successfully", signInParamsRequest.email());
        return ResponseEntity.ok(
                JwtTokenResponse.of(accessToken)
        );
    }

    @GetMapping("info")
    public ResponseEntity<UserResponse> getUserInfo(@AuthenticationPrincipal UserDetails principal) throws UserNotFoundException {
        logger.info("Getting user credentials by email {}", principal.getUsername());

        User user = findUserInputBoundary.findUserByEmail(principal.getUsername());

        logger.debug("User with email {} credentials received successfully", principal.getUsername());
        return ResponseEntity.ok(UserDtoMapper.mapUserToUserResponse(user));
    }
}

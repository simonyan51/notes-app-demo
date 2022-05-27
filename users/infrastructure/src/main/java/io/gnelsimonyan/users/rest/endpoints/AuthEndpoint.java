package io.gnelsimonyan.users.rest.endpoints;

import io.gnelsimonyan.users.boundaries.input.FindUserInputBoundary;
import io.gnelsimonyan.users.boundaries.input.SignInUserInputBoundary;
import io.gnelsimonyan.users.rest.endpoints.dtos.JwtTokenResponse;
import io.gnelsimonyan.users.rest.endpoints.dtos.SignInRequest;
import io.gnelsimonyan.users.rest.endpoints.dtos.UserResponse;
import io.gnelsimonyan.users.rest.mappers.UserDtoMapper;
import io.gnelsimonyan.users.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/auth")
public class AuthEndpoint {

    private final SignInUserInputBoundary signInUserInputBoundary;

    private final FindUserInputBoundary findUserInputBoundary;

    public AuthEndpoint(
            final SignInUserInputBoundary signInUserInputBoundary,
            final FindUserInputBoundary findUserInputBoundary
    ) {
        this.signInUserInputBoundary = signInUserInputBoundary;
        this.findUserInputBoundary = findUserInputBoundary;
    }

    @PostMapping("sign-in")
    public ResponseEntity<JwtTokenResponse> signIn(@RequestBody SignInRequest signInParamsRequest) {
        String accessToken = signInUserInputBoundary.signIn(
                UserDtoMapper.mapSignInUserParamsRequestToSignInUserParams(signInParamsRequest)
        );

        return ResponseEntity.ok(
                new JwtTokenResponse(accessToken)
        );
    }

    @GetMapping("info")
    public ResponseEntity<UserResponse> getUserInfo(@AuthenticationPrincipal UserDetails principal) {
        User user = findUserInputBoundary.findUserByEmail(principal.getUsername());

        return ResponseEntity.ok(UserDtoMapper.mapUserToUserResponse(user));
    }
}

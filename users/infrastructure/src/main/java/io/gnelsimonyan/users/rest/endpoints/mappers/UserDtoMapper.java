package io.gnelsimonyan.users.rest.endpoints.mappers;

import io.gnelsimonyan.users.rest.endpoints.dtos.SignInRequest;
import io.gnelsimonyan.users.rest.endpoints.dtos.UserResponse;
import io.gnelsimonyan.users.user.User;
import io.gnelsimonyan.users.boundaries.input.params.SignInUserParams;

public interface UserDtoMapper {

    static UserResponse mapUserToUserResponse(User user) {
        return new UserResponse(
                user.id(),
                user.email(),
                user.createdAt(),
                user.updatedAt()
        );
    }

    static SignInUserParams mapSignInUserParamsRequestToSignInUserParams(SignInRequest signInParamsRequest) {
        return SignInUserParams.of(
                signInParamsRequest.email(),
                signInParamsRequest.password()
        );
    }
}

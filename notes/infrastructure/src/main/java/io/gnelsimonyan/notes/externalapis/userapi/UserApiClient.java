package io.gnelsimonyan.notes.externalapis.userapi;

public interface UserApiClient {

    UserResponse getAuthorizedUser(String token);
}

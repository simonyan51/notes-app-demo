package io.gnelsimonyan.notes.externalapis.userapi;

public interface UserApiClient {

    AuthorizedUser getAuthorizedUser(String token);
}

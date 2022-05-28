package io.gnelsimonyan.notes.externalapis.userapi;

import io.gnelsimonyan.notes.externalapis.common.HttpClientExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

@Component
class UserApiClientImpl implements UserApiClient {

    private final String userApiClientUrl;

    public UserApiClientImpl(
            @Value("${users.host}")
            final String userApiClientHost,
            @Value("${users.port}")
            final String userApiClientPort
    ) {
        this.userApiClientUrl = "http://" + userApiClientHost + ":" + userApiClientPort + "/v1";
    }

    @Override
    public AuthorizedUser getAuthorizedUser(final String token) {
        return HttpClientExecutor
                .<AuthorizedUser>create()
                .setUrl(this.userApiClientUrl + "/auth/info")
                .setMethod(HttpMethod.GET)
                .setClassType(AuthorizedUser.class)
                .appendHeader("Authorization", token)
                .acceptJSON()
                .execute();
    }
}

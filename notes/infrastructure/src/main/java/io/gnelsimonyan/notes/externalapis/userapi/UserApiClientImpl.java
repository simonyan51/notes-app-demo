package io.gnelsimonyan.notes.externalapis.userapi;

import io.gnelsimonyan.notes.externalapis.common.HttpClientExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

@Component
class UserApiClientImpl implements UserApiClient {

    private final String userApiClientUrl;

    @Autowired
    public UserApiClientImpl(
            @Value("${users.host}")
            final String userApiClientHost,
            @Value("${users.port}")
            final String userApiClientPort
    ) {
        this.userApiClientUrl = "http://" + userApiClientHost + ":" + userApiClientPort + "/v1";
    }

    @Override
    public UserResponse getAuthorizedUser(final String token) {
        return new HttpClientExecutor<UserResponse>()
                .setUrl(this.userApiClientUrl + "/auth/info")
                .setMethod(HttpMethod.GET)
                .setClassType(UserResponse.class)
                .appendHeader("Authorization", token)
                .acceptJSON()
                .execute();
    }
}

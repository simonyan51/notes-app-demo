package io.gnelsimonyan.users.rest.endpoints.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtTokenResponse {
    private final String accessToken;

    public JwtTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }
}

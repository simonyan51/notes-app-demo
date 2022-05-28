package io.gnelsimonyan.users.rest.endpoints.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class JwtTokenResponse {

    @JsonProperty("access_token")
    private final String accessToken;
}

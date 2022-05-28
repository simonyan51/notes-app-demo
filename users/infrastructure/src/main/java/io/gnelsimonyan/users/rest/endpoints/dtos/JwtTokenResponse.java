package io.gnelsimonyan.users.rest.endpoints.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor(staticName = "of")
public class JwtTokenResponse {

    @JsonProperty("access_token")
    private final String accessToken;
}

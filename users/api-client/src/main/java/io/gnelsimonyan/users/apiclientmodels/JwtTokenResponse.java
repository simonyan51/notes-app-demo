package io.gnelsimonyan.users.apiclientmodels;

public interface JwtTokenResponse {
    String accessToken();

    static JwtTokenResponse of(
            String accessToken
    ) {
        return new JwtTokenResponseImpl(accessToken);
    }

    record JwtTokenResponseImpl(String accessToken) implements JwtTokenResponse {}
}

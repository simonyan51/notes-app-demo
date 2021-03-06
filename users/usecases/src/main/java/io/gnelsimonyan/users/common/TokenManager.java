package io.gnelsimonyan.users.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class TokenManager {

    private final String subject;

    private final String secret;

    public String generateToken(final String email) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject(subject)
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveSubject(final String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(subject)
                .build();

        DecodedJWT jwt = verifier.verify(token);

        return jwt.getClaim("email").asString();
    }

}

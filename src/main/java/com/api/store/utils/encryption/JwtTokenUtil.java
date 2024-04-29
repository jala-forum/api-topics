package com.api.store.utils.encryption;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

@Service
public class JwtTokenUtil {
    @Value("${api.security.token.secret}")
    private String secret;

    public Map<java.lang.String, Claim> getTokenClaims(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("parkingcontrol-api")
                .build()
                .verify(token)
                .getClaims();
    }

    public String getTokenSubject(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("parkingcontrol-api")
                .build()
                .verify(token)
                .getSubject();
    }
    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

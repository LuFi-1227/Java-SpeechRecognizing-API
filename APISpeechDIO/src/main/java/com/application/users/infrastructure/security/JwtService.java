package com.application.users.infrastructure.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.application.users.application.ports.TokenGateway;
import com.application.users.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component 
public class JwtService implements TokenGateway{

    @Value("${api.security.token.secret:minha-chave-secreta-super-forte-123456}")
    private String secret;

    @Override
    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
        .withIssuer("APISpeechDIO").withSubject(user.getId().id().toString()).withClaim("phone", user.getPhone().value())
        .withExpiresAt(Instant.now().plus(24, ChronoUnit.HOURS)).sign(algorithm);
    }

    @Override
    public String validateTokenAndGetUserID(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("APISpeechDIO").build().verify(token).getSubject();
        } catch (Exception e) {
            return null;
        }
    }
    
}

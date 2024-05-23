package com.codegenius.user.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.codegenius.user.domain.model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Service implementation for generating and extracting tokens.
 *
 * @author hidek
 * @since 2023-08-09
 */
@Service
public class TokenServiceImpl implements TokenService{
    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Generates a token for the provided user.
     *
     * @param user The user for whom the token is being generated.
     * @return The generated token as a string.
     * @throws RuntimeException If an error occurs during token creation.
     *
     * @author hidek
     * @since 2023-08-09
     */
    @Override
    public String token(UserModel user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Code Genius")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Create token error", exception);
        }
    }

    /**
     * Extracts and retrieves the subject (typically user ID) from a token.
     *
     * @param token The token from which to extract the subject.
     * @return The subject (e.g., user ID) extracted from the token.
     * @throws RuntimeException If an error occurs during token verification.
     *
     * @author hidek
     * @since 2023-08-09
     */
    @Override
    public String getSubject(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Code Genius")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Require token error", exception);
        }
    }

    // Private helper method for calculating token expiration time
    private Instant expiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

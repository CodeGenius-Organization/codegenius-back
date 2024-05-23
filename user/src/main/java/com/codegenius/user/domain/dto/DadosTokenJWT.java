package com.codegenius.user.domain.dto;

/**
 * A record class representing a JSON Web Token (JWT) data.
 *
 * @author hidek
 * @since 2023-08-09
 */
public record DadosTokenJWT(String token) {
    // This record class is used to hold the JWT token.
    // It has a single field 'token' which stores the JWT value.
}

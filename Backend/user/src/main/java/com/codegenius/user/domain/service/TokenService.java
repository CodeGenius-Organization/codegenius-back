package com.codegenius.user.domain.service;

import com.codegenius.user.domain.model.UserModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


/**
 * The TokenService interface provides methods for generating and extracting tokens.
 *
 * @author hidek
 * @since 2023-08-09
 */
public interface TokenService {
    /**
     * Generates a token for the provided user.
     *
     * @param user The user for whom the token is being generated.
     * @return The generated token as a string.
     *
     * @author hidek
     * @since 2023-08-09
     */
    String token(UserModel user);
    /**
     * Extracts and retrieves the subject (typically user ID) from a token.
     *
     * @param token The token from which to extract the subject.
     * @return The subject (e.g., user ID) extracted from the token.
     *
     * @author hidek
     * @since 2023-08-09
     */
    String getSubject(String token);
}

package com.codegenius.user.domain.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * The AuthenticationService interface extends UserDetailsService to provide user authentication details.
 *
 * @author hidek
 * @since 2023-08-09
 */
public interface AuthentificationService extends UserDetailsService {
    /**
     * Loads user details by their email for authentication.
     *
     * @param email The email of the user.
     * @return User details for authentication.
     *
     * @author hidek
     * @since 2023-08-09
     */
    UserDetails loadUserByUsername(String email);
}

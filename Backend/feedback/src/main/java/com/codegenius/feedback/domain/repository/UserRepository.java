package com.codegenius.feedback.domain.repository;

import com.codegenius.feedback.domain.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing user data.
 *
 * @author hidek
 * @since 2024-03-08
 */
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    /**
     * Finds a user by their email.
     *
     * @param email The email of the user to find.
     * @return An Optional containing the user with the given email, if found.
     *
     * @author hidek
     * @since 2024-03-08
     */
    Optional<UserModel> findByEmailAndActiveTrue(String email);
}

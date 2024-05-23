package com.codegenius.user.domain.repository;



import com.codegenius.user.domain.model.HeartModel;
import com.codegenius.user.domain.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository interface for managing game data.
 *
 * @author hidek
 * @since 2023-10-04
 */
public interface HeartRepository extends JpaRepository<HeartModel, UUID> {
    /**
     * Find a game by the foreign key user (fkUser).
     *
     * @param user The foreign key user to search for.
     * @return The game associated with the given fkUser, or null if not found.
     *
     * @author hidek
     * @since 2023-10-08
     */
    HeartModel findByFkUser(UserModel user);
}

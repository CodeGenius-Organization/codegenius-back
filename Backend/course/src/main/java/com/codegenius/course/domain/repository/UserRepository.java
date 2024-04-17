package com.codegenius.course.domain.repository;

import com.codegenius.course.domain.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing user data.
 *
 * @author hidek
 * @since 2023-08-09
 */
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    /**
     * Finds a user by their email.
     *
     * @param email The email of the user to find.
     * @return An Optional containing the user with the given email, if found.
     *
     * @author hidek
     * @since 2023-08-09
     */
    Optional<UserModel> findByEmailAndActiveTrue(String email);

    @Query(nativeQuery = true,
    value = """
            SELECT
                name AS name,
                email AS email
            FROM _user
            WHERE id_user = :teacherId
            """)
    Map<String, Object> findTeacherById(@Param("teacherId") UUID teacherId);
}

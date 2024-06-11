package com.codegenius.achievement.domain.repository;

import com.codegenius.achievement.domain.model.UserAchievementsModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface UserAchievementsRepository extends JpaRepository<UserAchievementsModel, UUID> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
    value = """
            UPDATE user_achievements ua SET ua.is_visible = NOT ua.is_visible WHERE ua.user_fk = :userId AND ua.achievement_fk = :achievementId 
            """)
    Integer setAchievementVisibility(@Param("userId") UUID userId, @Param("achievementId") UUID achievementId);

    List<UserAchievementsModel> findAllByUserFk(UUID userId);

    @Query(nativeQuery = true,
    value = """
            SELECT
                BIN_TO_UUID(a.achievements_id) id,
                a.title title,
                a.description description,
                a.points_needed pointsNeeded
            FROM achievements a
            LEFT JOIN user_achievements ua ON a.achievements_id = ua.achievement_fk AND ua.user_fk = :userId
            LEFT JOIN user_category_points ucp ON a.category_fk = ucp.category_fk AND ucp.user_fk = :userId
            LEFT JOIN user_language_points ulp ON a.language_fk = ulp.language_fk AND ulp.user_fk = :userId
            WHERE ua.achievement_fk IS NULL
            AND (
                (a.category_fk IS NOT NULL AND ucp.points >= a.points_needed)
                OR
                (a.language_fk IS NOT NULL AND ulp.points >= a.points_needed)
            );
            """)
    List<Map<String, Object>> verifyNewAchievements(@Param("userId") UUID userId);
}
package com.codegenius.achievement.domain.repository;

import com.codegenius.achievement.domain.model.UserCategoryPointsModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryPointsRepository extends JpaRepository<UserCategoryPointsModel, UUID> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = """
                    UPDATE user_category_points ucp SET ucp.points = ucp.points + :points WHERE user_fk = :userId AND category_fk = :category
                    """)
    void updatePoints(@Param("category") UUID category, @Param("points") Integer points, @Param("userId") UUID userId);

    Boolean existsByCategoryFkAndUserFk(UUID categoryFk, UUID userFk);
}
package com.codegenius.achievement.domain.repository;

import com.codegenius.achievement.domain.model.UserLanguagePointsModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LanguagePointsRepository extends JpaRepository<UserLanguagePointsModel, UUID> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = """
                    UPDATE user_language_points ulp SET ulp.points = ulp.points + :points WHERE ulp.user_fk = :userId AND ulp.language_fk = :language
                    """)
    void updatePoints(@Param("language") UUID language, @Param("points") Integer points, @Param("userId") UUID userId);

    Boolean existsByLanguageFkAndUserFk(UUID languageFk, UUID userFk);
}

package com.codegenius.achievement.domain.repository;

import com.codegenius.achievement.domain.model.AchievementsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AchievementsRepository extends JpaRepository<AchievementsModel, UUID> {

    Optional<AchievementsModel> findByTitle(String title);
}
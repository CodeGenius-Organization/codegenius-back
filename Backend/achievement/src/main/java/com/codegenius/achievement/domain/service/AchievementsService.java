package com.codegenius.achievement.domain.service;

import com.codegenius.achievement.domain.model.AchievementsModel;
import com.codegenius.achievement.domain.repository.AchievementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AchievementsService {
    @Autowired
    private AchievementsRepository repository;

    public AchievementsModel findAchievementByTitle(String title) {
        return repository.findByTitle(title).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Achievement with title " + title + " not found."));
    }

    public AchievementsModel findAchievementById(UUID achievementId) {
        return repository.findById(achievementId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Achievement with ID " + achievementId + " not found."));
    }

    public AchievementsModel createAchievement(AchievementsModel achievement) {
        return repository.save(achievement);
    }

    public List<AchievementsModel> findAllAchievements() {
        return repository.findAll();
    }
}
package com.codegenius.achievement.controller;

import com.codegenius.achievement.domain.model.AchievementsModel;
import com.codegenius.achievement.domain.service.AchievementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/achievements")
public class AchievementsController {
    @Autowired
    private AchievementsService service;

    @PostMapping("/")
    public ResponseEntity<AchievementsModel> createAchievement(@RequestBody AchievementsModel achievement) {
        return ResponseEntity.status(200).body(service.createAchievement(achievement));
    }

    @GetMapping("/")
    public ResponseEntity<List<AchievementsModel>> findAllAchievements() {
        List<AchievementsModel> achievements = service.findAllAchievements();
        int status = achievements.isEmpty() ? 204 : 200;
        return ResponseEntity.status(status).body(achievements);
    }
}

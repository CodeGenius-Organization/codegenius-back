package com.codegenius.achievement.controller;

import com.codegenius.achievement.domain.dto.UserAchievementRegistration;
import com.codegenius.achievement.domain.dto.UserAchievementsWithDetailsDTO;
import com.codegenius.achievement.domain.model.UserAchievementsModel;
import com.codegenius.achievement.domain.service.UserAchievementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user-achievements")
public class UserAchievementsController {
    @Autowired
    private UserAchievementsService service;

    @PostMapping("/")
    public ResponseEntity<UserAchievementsModel> registerNewAchievement(@RequestBody UserAchievementRegistration data) {
        return ResponseEntity.status(201).body(service.registerNewAchievement(data));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserAchievementsWithDetailsDTO> getAllUserAchievements(@PathVariable UUID userId) {
        return ResponseEntity.status(200).body(service.getAllUserAchievements(userId));
    }

    @PatchMapping("/visibility/{userId}/{achievementId}")
    public ResponseEntity<Integer> setAchievementVisibility(@PathVariable UUID userId, @PathVariable UUID achievementId) {
        return ResponseEntity.status(200).body(service.setAchievementVisibility(userId, achievementId));
    }

    @GetMapping("/new/{userId}")
    public ResponseEntity<List<Map<String, Object>>> verifyNewAchievements(@PathVariable UUID userId) {
        return ResponseEntity.status(200).body(service.verifyNewAchievements(userId));
    }
}
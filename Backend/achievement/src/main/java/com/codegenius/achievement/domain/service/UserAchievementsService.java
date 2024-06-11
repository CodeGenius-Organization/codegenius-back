package com.codegenius.achievement.domain.service;

import com.codegenius.achievement.domain.dto.SimpleAchievementDTO;
import com.codegenius.achievement.domain.dto.UserAchievementRegistration;
import com.codegenius.achievement.domain.dto.UserAchievementsWithDetailsDTO;
import com.codegenius.achievement.domain.model.AchievementsModel;
import com.codegenius.achievement.domain.model.UserAchievementsModel;
import com.codegenius.achievement.domain.model.UserModel;
import com.codegenius.achievement.domain.repository.UserAchievementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class UserAchievementsService {
    @Autowired
    private UserAchievementsRepository repository;

    @Autowired
    private AchievementsService achievementsService;

    @Autowired
    private CustomUserDetailsService userService;


    public UserAchievementsModel registerNewAchievement(UserAchievementRegistration data) {
        AchievementsModel achievement = achievementsService.findAchievementById(data.getAchievementFk());
        UserAchievementsModel newUserAchievement = new UserAchievementsModel();
        newUserAchievement.setAchievementFk(data.getAchievementFk());
        newUserAchievement.setUserFk(data.getUserFk());
        newUserAchievement.setDateAchieved(LocalDate.parse(data.getDateAchieved(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        newUserAchievement.setIsVisible(true);
        return repository.save(newUserAchievement);
    }

    public Integer setAchievementVisibility(UUID userId, UUID achievementId) {
        return repository.setAchievementVisibility(userId, achievementId);
    }

    public UserAchievementsWithDetailsDTO getAllUserAchievements(UUID userId) {
        List<SimpleAchievementDTO> simpleAchievements = new ArrayList<>();
        UserAchievementsWithDetailsDTO userAchievementsWithUserDetails = new UserAchievementsWithDetailsDTO();

        List<UserAchievementsModel> userAchievements = repository.findAllByUserFk(userId);

        for (UserAchievementsModel item : userAchievements) {
            AchievementsModel achievement = achievementsService.findAchievementById(item.getAchievementFk());
            SimpleAchievementDTO simpleAchievement = new SimpleAchievementDTO(achievement.getTitle(), achievement.getDescription(), item.getIsVisible());
            simpleAchievements.add(simpleAchievement);
        }

        userAchievementsWithUserDetails.setListaDeConquistas(simpleAchievements);

        UserModel user = userService.findById(userId);

        userAchievementsWithUserDetails.setNome(user.getName());

        return userAchievementsWithUserDetails;
    }

    public List<Map<String, Object>> verifyNewAchievements(UUID userId) {
        List<Map<String, Object>> newAchievements = repository.verifyNewAchievements(userId);
        for (Map<String, Object> achievement : newAchievements) {
            UserAchievementRegistration newAchievement = new UserAchievementRegistration(userId, UUID.fromString(achievement.get("id").toString()), LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            registerNewAchievement(newAchievement);
        }

        return newAchievements;
    }
}
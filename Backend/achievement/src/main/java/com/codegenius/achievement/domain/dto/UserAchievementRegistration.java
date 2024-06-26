package com.codegenius.achievement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAchievementRegistration {
    private UUID userFk;
    private UUID achievementFk;
    private String dateAchieved;
}
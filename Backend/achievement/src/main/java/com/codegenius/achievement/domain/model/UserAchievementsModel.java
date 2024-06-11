package com.codegenius.achievement.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "user_achievements")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAchievementsModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "user_achievement_id")
    private UUID id;

    @Column(name = "user_fk")
    private UUID userFk;

    @Column(name = "achievement_fk")
    private UUID achievementFk;

    @Column(name = "date_achieved")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateAchieved;

    @Column(name = "is_visible")
    private Boolean isVisible;
}
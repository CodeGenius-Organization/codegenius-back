package com.codegenius.achievement.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "achievements")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AchievementsModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "achievements_id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "points_needed")
    private Integer pointsNeeded;

    @Column(name = "category_fk")
    private UUID categoryFk;

    @Column(name = "language_fk")
    private UUID languageFk;
}
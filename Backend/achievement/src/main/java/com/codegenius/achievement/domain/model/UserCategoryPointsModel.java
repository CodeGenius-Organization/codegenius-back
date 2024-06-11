package com.codegenius.achievement.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "user_category_points")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCategoryPointsModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "user_category_points_id")
    private UUID id;

    @Column(name = "points")
    private Integer points;

    @Column(name = "category_fk", columnDefinition = "uuid")
    private UUID categoryFk;

    @Column(name = "user_fk", columnDefinition = "uuid")
    private UUID userFk;
}
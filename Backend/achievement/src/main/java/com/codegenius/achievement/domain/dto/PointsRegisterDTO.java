package com.codegenius.achievement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PointsRegisterDTO {
    private List<UUID> categories;
    private List<UUID> languages;
    private Integer points = 0;
}
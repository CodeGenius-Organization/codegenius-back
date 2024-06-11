package com.codegenius.achievement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SimpleAchievementDTO {
    private String nome;
    private String descricao;
    private Boolean visivel;
}
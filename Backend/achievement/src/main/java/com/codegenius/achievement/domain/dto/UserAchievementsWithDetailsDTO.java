package com.codegenius.achievement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAchievementsWithDetailsDTO {
    private String nome;
    private String sobrenome;
    private List<SimpleAchievementDTO> listaDeConquistas;
}

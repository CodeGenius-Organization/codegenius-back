package com.codegenius.game.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resposta {
    @NotBlank
    private String resposta;
    @NotNull
    private Boolean correta;
    @NotBlank
    private String explicacao;
    @NotNull
    private UUID fkQuestao;
}

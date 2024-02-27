package com.codegenius.game.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListaQuestao {
    @NotBlank
    private UUID id;
    @NotBlank
    private String answer;

    @NotBlank
    private String statement;

    @NotBlank
    private Boolean testQuestion;

    @NotNull
    private UUID lesson_content;

    @NotEmpty
    private List<Resposta> respostas;
}

package com.codegenius.game.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class DadosQuestoesUpdate {
    @JsonProperty("tipo")
    private String question_type;
    @JsonProperty("enunciado")
    private String statement;
    @JsonProperty("teste")
    private Boolean test_question;
    @JsonProperty("fkLessonContent")
    private UUID lesson_content;
}

package com.codegenius.game.domain.dto;

import com.codegenius.game.domain.model.QuestionModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class DadosQuestoes {
    @JsonProperty("tipo")
    @NotBlank
    private String question_type;
    @JsonProperty("enunciado")
    @NotBlank
    private String statement;
    @JsonProperty("teste")
    @NotNull
    private Boolean test_question;
    @JsonProperty("fkLessonContent")
    @NotNull
    private UUID lesson_content;
}

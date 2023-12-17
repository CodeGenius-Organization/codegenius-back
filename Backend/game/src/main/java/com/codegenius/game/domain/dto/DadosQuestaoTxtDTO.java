package com.codegenius.game.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosQuestaoTxtDTO {

    @NotBlank
    private String questionType;
    @NotBlank
    private String statement;
    @NotBlank
    private Boolean testQuestion;
    @NotBlank
    private UUID lessonContent;
    @NotBlank
    private String questionDifficulty;

    @Override
    public String toString() {
        return "DadosQuestaoTxtDTO{" +
                "questionType='" + questionType + '\'' +
                ", statement='" + statement + '\'' +
                ", testQuestion=" + testQuestion +
                ", lessonContent=" + lessonContent +
                ", questionDifficulty='" + questionDifficulty + '\'' +
                '}';
    }
}

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
public class DadosRespostaTxtDTO {


    @NotBlank
    private String answer;

    @NotBlank
    private String statement;

    @NotBlank
    private Integer testQuestion;

    @NotBlank
    private UUID lessonContentFk;
}

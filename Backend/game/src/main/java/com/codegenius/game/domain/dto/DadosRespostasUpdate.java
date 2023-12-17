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
public class DadosRespostasUpdate {
    @JsonProperty("resposta")
    private String answer;
    @JsonProperty("correta")
    private Boolean correct;
    @JsonProperty("explicacao")
    private String explanation;
    @JsonProperty("fkQuestao")
    private UUID fkQuestion;
}

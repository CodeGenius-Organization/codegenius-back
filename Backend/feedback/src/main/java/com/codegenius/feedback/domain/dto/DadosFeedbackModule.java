package com.codegenius.feedback.domain.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosFeedbackModule {
    @DecimalMax(value = "5.0")
    @DecimalMin(value = "0.0")
    private Double stars;
    private String feedback;
    @NotNull
    private UUID moduleFk;
    @NotNull
    private UUID userFk;
}

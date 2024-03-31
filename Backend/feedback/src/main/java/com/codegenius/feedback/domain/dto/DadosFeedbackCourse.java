package com.codegenius.feedback.domain.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
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
public class DadosFeedbackCourse {
    @DecimalMax(value = "5.0")
    @DecimalMin(value = "0.0")
    private Double feedback;
    private String feedbackDescription;
    @NotNull
    private UUID courseFk;
    @NotNull
    private UUID userFk;
}

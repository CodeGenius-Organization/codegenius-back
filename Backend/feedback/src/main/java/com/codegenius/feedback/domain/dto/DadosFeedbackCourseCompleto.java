package com.codegenius.feedback.domain.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
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
public class DadosFeedbackCourseCompleto {
    private UUID id;
    private Integer stars;
    private String feedback;
    private Date feedbackDate;
    private UUID courseFk;
    private UUID userFk;
}

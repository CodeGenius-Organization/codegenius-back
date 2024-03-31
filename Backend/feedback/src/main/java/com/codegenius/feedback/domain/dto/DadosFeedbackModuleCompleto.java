package com.codegenius.feedback.domain.dto;

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
public class DadosFeedbackModuleCompleto {
    private UUID id;
    private Double stars;
    private String feedback;
    private Date feedbackDate;
    private UUID moduleFk;
    private UUID userFk;
}

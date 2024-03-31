package com.codegenius.course.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModuleFeedbackCreationDTO {
    @NotNull
    private UUID moduleFk;
    @NotNull
    private UUID userFk;
    @NotNull
    private Integer stars;
    private String feedback;
    @NotNull
    private String feedbackDate;
}
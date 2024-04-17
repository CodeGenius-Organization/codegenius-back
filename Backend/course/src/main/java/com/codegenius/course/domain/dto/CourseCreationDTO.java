package com.codegenius.course.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseCreationDTO {

    @NotBlank
    private String title;
    @NotBlank
    private String courseDescription;
    @NotBlank
    private String contentDescription;
    @NotNull
    private Boolean available;
    @NotNull
    private List<UUID> languageIds;
    @NotNull
    private List<UUID> categoryIds;
    @NotNull
    private UUID teacherFk;
}
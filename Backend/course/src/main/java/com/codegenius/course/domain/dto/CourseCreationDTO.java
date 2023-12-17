package com.codegenius.course.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<UUID> getLanguageIds() {
        return languageIds;
    }

    public void setLanguageIds(List<UUID> languageIds) {
        this.languageIds = languageIds;
    }

    public List<UUID> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<UUID> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
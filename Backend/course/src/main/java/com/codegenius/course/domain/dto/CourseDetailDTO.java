package com.codegenius.course.domain.dto;

import com.codegenius.course.domain.model.CourseModel;
import com.codegenius.course.domain.model.CourseModuleModel;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public class CourseDetailDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String courseDescription;

    @NotBlank
    private String contentDescription;

    private Set<CourseModuleModel> modules;

    public CourseDetailDTO() {
    }

    public CourseDetailDTO(CourseModel c) {
        this.title = c.getTitle();
        this.courseDescription = c.getCourseDescription();
        this.contentDescription = c.getContentDescription();
        this.modules = c.getModules();
    }

    public String getTitle() {
        return title;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public Set<CourseModuleModel> getModules() {
        return modules;
    }
}

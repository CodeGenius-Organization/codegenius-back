package com.codegenius.course.domain.dto;

import com.codegenius.course.domain.model.CategoryModel;
import com.codegenius.course.domain.model.CourseModel;
import com.codegenius.course.domain.model.CourseModuleModel;
import com.codegenius.course.domain.model.LanguageModel;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String courseDescription;

    @NotBlank
    private String contentDescription;

    private Set<CourseModuleModel> modules;

    private List<String> languages;

    private List<String> categories;

    public CourseDetailDTO(CourseModel c) {
        this.title = c.getTitle();
        this.courseDescription = c.getCourseDescription();
        this.contentDescription = c.getContentDescription();
        this.modules = c.getModules();
        this.languages = c.getLanguages().stream().map(language -> language.getId().toString()).toList();
        this.categories = c.getCategories().stream().map(category -> category.getId().toString()).toList();
    }
}

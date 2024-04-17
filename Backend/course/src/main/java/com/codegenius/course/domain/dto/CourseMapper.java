package com.codegenius.course.domain.dto;

import com.codegenius.course.domain.model.CategoryModel;
import com.codegenius.course.domain.model.CourseModel;
import com.codegenius.course.domain.model.LanguageModel;

import java.util.HashSet;
import java.util.List;

public class CourseMapper {

    public static CourseModel of(CourseCreationDTO courseCreationDTO, List<LanguageModel> languages, List<CategoryModel> categories) {

        CourseModel course = new CourseModel();

        if (courseCreationDTO.getTitle() != null)
            course.setTitle(courseCreationDTO.getTitle());

        if (courseCreationDTO.getCourseDescription() != null)
            course.setCourseDescription(courseCreationDTO.getCourseDescription());

        if (courseCreationDTO.getContentDescription() != null)
            course.setContentDescription(courseCreationDTO.getContentDescription());

        if (courseCreationDTO.getAvailable() != null)
            course.setAvailable(courseCreationDTO.getAvailable());

        if (courseCreationDTO.getTeacherFk() != null)
            course.setTeacherFk(courseCreationDTO.getTeacherFk());

        if (!languages.isEmpty())
            course.setLanguages(new HashSet<>(languages));

        if (!categories.isEmpty())
            course.setCategories(new HashSet<>(categories));

        return course;
    }
}
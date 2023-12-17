package com.codegenius.course.domain.dto;

import com.codegenius.course.domain.model.CourseModel;
import com.codegenius.course.domain.model.CourseModuleModel;

public class CourseModuleMapper {

    public static CourseModuleModel of(CourseModuleCreationDTO courseModuleCreationDTO, CourseModel course) {
        CourseModuleModel courseModule = new CourseModuleModel();

        courseModule.setCourse(course);
        courseModule.setModuleName(courseModuleCreationDTO.getModuleName());
        courseModule.setModuleOrder(courseModuleCreationDTO.getModuleOrder());

        return courseModule;
    }
}

package com.codegenius.course.domain.dto;

import java.util.Map;
import java.util.UUID;

public class TeacherCourseMapper {
    public static TeacherCourseDTO of(Map<String, Object> item) {
        TeacherCourseDTO dto = new TeacherCourseDTO();
        dto.setCourseId(UUID.fromString(item.get("courseId").toString()));
        dto.setTitle((String) item.get("title"));
        dto.setDescription((String) item.get("description"));
        dto.setUnreadFeedbacks(Math.toIntExact((Long) item.get("unreadFeedbacks")));
        dto.setUnreadNegativeFeedbacks(Math.toIntExact((Long) item.get("unreadNegativeFeedbacks")));

        return dto;
    }
}
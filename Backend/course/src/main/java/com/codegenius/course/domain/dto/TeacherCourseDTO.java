package com.codegenius.course.domain.dto;

import com.codegenius.course.domain.model.LanguageModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeacherCourseDTO {
    private UUID courseId;
    private String title;
    private String description;
    private List<LanguageModel> languages;
    private Integer unreadFeedbacks;
    private Integer unreadNegativeFeedbacks;
}
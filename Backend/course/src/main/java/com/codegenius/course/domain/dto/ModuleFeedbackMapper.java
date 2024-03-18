package com.codegenius.course.domain.dto;

import com.codegenius.course.domain.model.CourseModuleModel;
import com.codegenius.course.domain.model.ModuleFeedback;
import com.codegenius.course.domain.model.UserModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ModuleFeedbackMapper {
    public static ModuleFeedback of(ModuleFeedbackCreationDTO dto, UserModel user, CourseModuleModel module) {
        ModuleFeedback moduleFeedback = new ModuleFeedback();

        moduleFeedback.setFeedback(dto.getFeedback());
        LocalDate feedbackDate = LocalDate.parse(dto.getFeedbackDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        moduleFeedback.setFeedbackDate(feedbackDate);
        moduleFeedback.setUser(user);
        moduleFeedback.setModule(module);
        moduleFeedback.setStars(dto.getStars());

        return moduleFeedback;
    }
}

package com.codegenius.course.domain.dto;

import com.codegenius.course.domain.model.LessonTestAttemptId;
import com.codegenius.course.domain.model.LessonTestAttempts;
import com.codegenius.course.domain.model.ModuleLessonModel;
import com.codegenius.course.domain.model.UserModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestAttemptMapper {
    public static LessonTestAttempts of(TestAttemptDTO dto, UserModel user, ModuleLessonModel moduleLesson) {
        LessonTestAttempts attempt = new LessonTestAttempts();

        attempt.setUser(user);
        attempt.setModuleLesson(moduleLesson);
//        LessonTestAttemptId id = new LessonTestAttemptId(dto.getUserId(), dto.getModuleLessonId());
//
//        attempt.setId(id);
        attempt.setAttemptDuration(dto.getAttemptDuration());
        attempt.setScore(dto.getScore());
        LocalDateTime attemptStartDate = LocalDateTime.parse(dto.getAttemptStartDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        attempt.setAttemptStartDate(attemptStartDate);

        return attempt;
    }
}

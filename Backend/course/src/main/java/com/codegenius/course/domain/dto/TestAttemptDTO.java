package com.codegenius.course.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TestAttemptDTO {
    private UUID userId;
    private UUID moduleLessonId;
    private Integer score;
    private String attemptStartDate;
    private String attemptDuration;
}

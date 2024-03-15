package com.codegenius.course.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class LessonTestAttemptId implements Serializable {
    @Column(name = "user_fk")
    private UUID userId;
    @Column(name = "module_lesson_fk")
    private UUID moduleLessonId;

    private LessonTestAttemptId() {}

    public LessonTestAttemptId(UUID userId, UUID moduleLessonId) {
        this.userId = userId;
        this.moduleLessonId = moduleLessonId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getModuleLessonId() {
        return moduleLessonId;
    }

    public void setModuleLessonId(UUID moduleLessonId) {
        this.moduleLessonId = moduleLessonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LessonTestAttemptId that)) return false;
        return Objects.equals(userId, that.userId) && Objects.equals(moduleLessonId, that.moduleLessonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, moduleLessonId);
    }
}

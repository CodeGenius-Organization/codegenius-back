package com.codegenius.course.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "lesson_test_attempts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LessonTestAttempts {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "lesson_test_attempt_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserModel user;

    @ManyToOne(fetch = FetchType.LAZY)
    private ModuleLessonModel moduleLesson;

    @Column(name = "score")
    private Integer score;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "attempt_start_date")
    private LocalDateTime attemptStartDate;

    @Column(name = "attempt_duration")
    private String attemptDuration;

//    public LessonTestAttempts(UserModel user, ModuleLessonModel moduleLesson) {
//        this.user = user;
//        this.moduleLesson = moduleLesson;
//        this.id = new LessonTestAttemptId(user.getId(), moduleLesson.getId());
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof LessonTestAttempts that)) return false;
//        return Objects.equals(user, that.user) && Objects.equals(moduleLesson, that.moduleLesson);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(user, moduleLesson);
//    }
}
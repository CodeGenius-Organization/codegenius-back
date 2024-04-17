package com.codegenius.feedback.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "course_feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackCourseModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "course_feedback_id", length = 16, columnDefinition = "uuid")
    private UUID id;
    @Column(name = "stars", nullable = false)
    private Integer stars;
    @Column(name = "feedback")
    private String feedback;
    @Column(name = "feedback_date", nullable = false)
    private Date feedbackDate;
    @Column(name = "is_read", nullable = false)
    private boolean isRead;
    @Column(name = "course_fk", nullable = false)
    private UUID courseFk;
    @Column(name = "user_fk", nullable = false)
    private UUID userFk;
}

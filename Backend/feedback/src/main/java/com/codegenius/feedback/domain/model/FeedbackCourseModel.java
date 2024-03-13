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
    @Column(name = "feedback", length = 3, nullable = false)
    private Double feedback;
    @Column(name = "feedback_description")
    private String feedbackDescription;
    @Column(name = "feedback_date", nullable = false)
    private Date feedbackDate;
    @Column(name = "course_fk", nullable = false)
    private UUID courseFk;
    @Column(name = "user_fk", nullable = false)
    private UUID userFk;
}

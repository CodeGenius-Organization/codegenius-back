package com.codegenius.feedback.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "module_feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackModuleModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "module_feedback_id", length = 16, columnDefinition = "uuid")
    private UUID id;
    @Column(name = "feedback", length = 3, nullable = false)
    private Double feedback;
    @Column(name = "feedback_description")
    private String feedbackDescription;
    @Column(name = "feedback_date", nullable = false)
    private Date feedbackDate;
    @Column(name = "module_fk", nullable = false)
    private UUID moduleFk;
    @Column(name = "user_fk", nullable = false)
    private UUID userFk;
}

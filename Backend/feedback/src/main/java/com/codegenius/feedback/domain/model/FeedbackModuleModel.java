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
    @Column(name = "stars", length = 3, nullable = false)
    private Integer stars;
    @Column(name = "feedback")
    private String feedback;
    @Column(name = "feedback_date", nullable = false)
    private Date feedbackDate;
    @Column(name = "module_fk", nullable = false)
    private UUID moduleFk;
    @Column(name = "user_fk", nullable = false)
    private UUID userFk;
}

package com.codegenius.course.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "module_feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModuleFeedback {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "module_feedback_id", length = 16, columnDefinition = "uuid")
    private UUID id;
    @Column(name = "stars", length = 3, nullable = false)
    private Integer stars;
    @Column(name = "feedback")
    private String feedback;
    @Column(name = "feedback_date", nullable = false)
    private LocalDate feedbackDate;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_fk")
    private CourseModuleModel module;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk")
    private UserModel user;

}
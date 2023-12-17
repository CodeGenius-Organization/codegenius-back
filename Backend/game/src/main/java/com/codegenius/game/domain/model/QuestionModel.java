package com.codegenius.game.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * Entity class representing question-related information for a user stored in the database.
 *
 * @author hidek
 * @since 2023-10-08
 */
@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id_question", length = 16, columnDefinition = "uuid")
    private UUID id;
    @Column(name = "question_type", length = 50)
    private String questionType;
    @Column(name = "statement", length = 250)
    private String statement;
    @Column(name = "test_question")
    private Boolean testQuestion;
    @Column(name = "lesson_content_fk")
    private UUID leassonContent;
}

package com.codegenius.game.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id_response", length = 16, columnDefinition = "uuid")
    private UUID id;
    @Column(name = "answer", length = 255, nullable = false)
    private String answer;
    @Column(name = "correct")
    private Boolean correct;
    @Column(name = "explanation", length = 255, nullable = false)
    private String explanation;
    @ManyToOne
    @JoinColumn(name = "fk_question")
    private QuestionModel fkQuestion;
}

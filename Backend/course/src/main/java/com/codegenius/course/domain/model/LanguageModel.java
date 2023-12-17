package com.codegenius.course.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "language")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanguageModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "language_id", length = 16, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "language", length = 45, nullable = false)
    @NotBlank
    private String language;

    @ManyToMany(mappedBy = "languages")
    @JsonIgnore
    private Set<CourseModel> courses = new HashSet<>();

    public LanguageModel(String language) {
        this.language = language;
    }
}
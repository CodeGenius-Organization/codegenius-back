package com.codegenius.course.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "lesson_content")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonContentModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "lesson_content_id", length = 16, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "title", nullable = false, length = 45)
    @NotBlank
    private String title;

    @Column(name = "content", nullable = false, length = 1200)
    @NotBlank
    private String content;

    @JsonIgnore
    @Column(name = "media", columnDefinition = "BLOB")
    private byte[] media;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "module_lesson_fk", referencedColumnName = "module_lesson_id")
    private ModuleLessonModel moduleLesson;
}

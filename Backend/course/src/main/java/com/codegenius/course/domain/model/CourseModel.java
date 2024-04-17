package com.codegenius.course.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;


/**
 * Entity class representing a course.
 *
 * @author hidek
 * @since 2023-09-28
 */
@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "course_id", length = 16, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "title", length = 45, nullable = false)
    @NotBlank
    private String title;

    @Column(name = "course_description", length = 100, nullable = false)
    @NotBlank
    @JsonIgnore
    private String courseDescription;

    @Column(name = "content_description", length = 100, nullable = false)
    @NotBlank
    @JsonIgnore
    private String contentDescription;

    @JsonIgnore
    @Column(name = "image", nullable = false, columnDefinition = "BLOB")
    private byte[] image;

    @Column(name = "available", nullable = false)
    @NotNull
    private Boolean available;

    @Column(name = "teacher_fk")
    private UUID teacherFk;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "course_languages",
            joinColumns = @JoinColumn(name = "course_fk"),
            inverseJoinColumns = @JoinColumn(name = "language_fk"))
    private Set<LanguageModel> languages = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "course_categories",
            joinColumns = @JoinColumn(name = "course_fk"),
            inverseJoinColumns = @JoinColumn(name = "category_fk"))
    private Set<CategoryModel> categories = new HashSet<>();

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private Set<CourseModuleModel> modules = new HashSet<>();

    public CourseModel(String title, String courseDescription, String contentDescription, Boolean available) {
        this.title = title;
        this.courseDescription = courseDescription;
        this.contentDescription = contentDescription;
        this.available = available;
    }
}

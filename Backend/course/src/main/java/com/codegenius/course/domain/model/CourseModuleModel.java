package com.codegenius.course.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "course_module")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseModuleModel {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "course_module_id", length = 16, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "module_name", length = 30, nullable = false)
    @NotBlank
    private String moduleName;

    @Column(name = "module_order", length = 10, nullable = false)
    @NotNull
    private Integer moduleOrder;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_fk")
    private CourseModel course;

    @OneToMany(
            mappedBy = "module",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ModuleLessonModel> lessons = new HashSet<>();
}
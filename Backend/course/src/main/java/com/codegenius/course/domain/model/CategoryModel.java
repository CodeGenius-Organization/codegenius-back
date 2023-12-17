package com.codegenius.course.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "category_id", length = 16, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "category", length = 20, nullable = false)
    @NotBlank
    private String category;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<CourseModel> courses = new HashSet<>();

    public CategoryModel(String category) {
        this.category = category;
    }
}
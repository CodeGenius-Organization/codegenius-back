package com.codegenius.course.domain.repository;

import com.codegenius.course.domain.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, UUID> {

    List<CategoryModel> findCategoryByIdIn(List<UUID> ids);

    CategoryModel findByCategory(String name);
}
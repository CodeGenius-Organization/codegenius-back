package com.codegenius.course.domain.service;

import com.codegenius.course.domain.model.CategoryModel;
import com.codegenius.course.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryModel createCategory(CategoryModel category) {
        return this.categoryRepository.save(category);
    }

    public List<CategoryModel> findCategoryByIdIn(List<UUID> ids) {
        return this.categoryRepository.findCategoryByIdIn(ids);
    }

    public List<CategoryModel> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    public CategoryModel findCategoryById(UUID categoryId) {
        Optional<CategoryModel> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria de id " + category.get().getId() + " não encontrada.");
        }
        return category.get();
    }

    public CategoryModel findCategoryByName(String name) {
        CategoryModel category = categoryRepository.findByCategory(name);
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria de nome " + category.getCategory() + " não encontrada.");
        }
        return category;
    }
}

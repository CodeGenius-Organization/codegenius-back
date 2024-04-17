package com.codegenius.course.domain.repository;

import com.codegenius.course.domain.model.LanguageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageModel, UUID> {

    List<LanguageModel> findLanguageByIdIn(List<UUID> ids);
    Optional<LanguageModel> findLanguageByLanguage(String name);

    List<LanguageModel> findByCourses_Id(UUID courseId);
}
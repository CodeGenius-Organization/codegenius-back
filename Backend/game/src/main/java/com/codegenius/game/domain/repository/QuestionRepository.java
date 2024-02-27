package com.codegenius.game.domain.repository;

import com.codegenius.game.domain.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<QuestionModel, UUID> {
    List<QuestionModel> findByLeassonContent(UUID lessonContent);
}

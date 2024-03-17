package com.codegenius.feedback.domain.repository;

import com.codegenius.feedback.domain.model.FeedbackModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FeedbackModuleRepository extends JpaRepository<FeedbackModuleModel, UUID> {
    List<FeedbackModuleModel> findAllByModuleFk(UUID moduloFk);
}

package com.codegenius.course.domain.repository;

import com.codegenius.course.domain.model.ModuleFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ModuleFeedbackRepository extends JpaRepository<ModuleFeedback, UUID> {
}
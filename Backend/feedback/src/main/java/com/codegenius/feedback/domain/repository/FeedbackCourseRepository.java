package com.codegenius.feedback.domain.repository;

import com.codegenius.feedback.domain.model.FeedbackCourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FeedbackCourseRepository extends JpaRepository<FeedbackCourseModel, UUID> {
    List<FeedbackCourseModel> findAllByCourseFk(UUID courseFk);
}

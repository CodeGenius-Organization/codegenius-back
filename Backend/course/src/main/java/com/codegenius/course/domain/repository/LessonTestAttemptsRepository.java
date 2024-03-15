package com.codegenius.course.domain.repository;

import com.codegenius.course.domain.model.LessonTestAttempts;
import com.codegenius.course.domain.model.LessonTestAttemptId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonTestAttemptsRepository extends JpaRepository<LessonTestAttempts, LessonTestAttemptId> {

}

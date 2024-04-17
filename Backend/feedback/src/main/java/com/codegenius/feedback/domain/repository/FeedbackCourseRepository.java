package com.codegenius.feedback.domain.repository;

import com.codegenius.feedback.domain.model.FeedbackCourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface FeedbackCourseRepository extends JpaRepository<FeedbackCourseModel, UUID> {
    List<FeedbackCourseModel> findAllByCourseFk(UUID courseFk);

    @Query(nativeQuery = true,
    value = """
            SELECT
                c.course_id courseId,
                c.description description,
                cf.course_feedback_id id,
                cf.stars rate,
                cf.feedback feedbackDescription
            FROM course_feedback cf
            JOIN course c
                ON c.id = cf.course_fk
            WHERE c.teacher_fk = :teacherFk
            ORDER BY cf.stars ASC;
            """)
    Map<String, Object> findAllByTeacherFk(@Param("teacherFk") UUID teacherFk);

    List<FeedbackCourseModel> findAllByCourseFkOrderByStarsAsc(UUID courseFk);
}
package com.codegenius.course.domain.repository;

import com.codegenius.course.domain.dto.CourseCsvDTO;
import com.codegenius.course.domain.dto.TeacherCourseDTO;
import com.codegenius.course.domain.model.CourseModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing course data.
 *
 * @author hidek
 * @since 2023-09-028
 */
public interface CourseRepository extends JpaRepository<CourseModel, UUID> {

    List<CourseModel> findByLanguages_Id(UUID id);

    List<CourseModel> findByCategories_Id(UUID id);

    List<CourseModel> findByAvailableIsTrue();

    List<CourseModel> findByAvailableIsTrueAndTitleContaining(String keyword);


    @Query("""
            select new com.codegenius.course.domain.dto.CourseCsvDTO(c.title,c.courseDescription, c.contentDescription, c.available) from CourseModel c
            """)
    List<CourseCsvDTO> pegarAllCsv();

    @Modifying
    @Transactional
    @Query("""
            UPDATE CourseModel c SET c.image = ?2
            WHERE c.id = ?1
            """)
    void updateCourseImage(UUID courseId, byte[] image);

    @Query("SELECT c.image FROM CourseModel c WHERE c.id = ?1")
    byte[] getCourseImage(UUID courseId);

    @Query(nativeQuery = true, value =
            """
            SELECT
                    c.*,
                    cc.*,
                    cat.*
            FROM course c
            JOIN course_categories cc
                ON cc.course_fk = c.course_id
            JOIN category cat
                ON cat.category = :categoryName
                AND cc.category_fk = cat.category_id
            ORDER BY
                CASE
                    WHEN :ordering = 'ASC' THEN c.title
                    ELSE NULL
                END ASC,
                CASE
                    WHEN :ordering = 'DESC' THEN c.title
                    ELSE NULL
                END DESC
            LIMIT 12 OFFSET :position
            """)
    //WHEN ?2 = 'STAR' THEN ordenar por avaliacao de curso
    List<CourseModel> findByCategories_Category_OrderBy(@Param("categoryName") String categoryName, @Param("ordering") String ordering, @Param("position") Integer position);

    @Query(value =
            """
            SELECT
                BIN_TO_UUID(c.course_id) courseId,
                c.title title,
                c.course_description description,
                COALESCE(COUNT(CASE WHEN cf.is_read = 0 THEN 1 ELSE NULL END), 0) AS unreadFeedbacks,
                COALESCE(COUNT(CASE WHEN cf.is_read = 0 AND cf.stars <= 2 THEN 1 ELSE NULL END), 0) AS unreadNegativeFeedbacks 
            FROM course c
            LEFT JOIN course_feedback cf
                ON cf.course_fk = c.course_id
            WHERE c.teacher_fk = :teacherId
            GROUP BY c.course_id
            ORDER BY unreadFeedbacks DESC;
            """, nativeQuery = true)
    List<Map<String, Object>> findAllTeacherCourses(@Param("teacherId") UUID teacherId);
}
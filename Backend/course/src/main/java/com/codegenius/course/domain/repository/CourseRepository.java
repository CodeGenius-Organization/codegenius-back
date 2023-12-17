package com.codegenius.course.domain.repository;

import com.codegenius.course.domain.dto.CourseCsvDTO;
import com.codegenius.course.domain.model.CourseModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
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

    List<CourseModel> findByCategories_Category(String name);
}

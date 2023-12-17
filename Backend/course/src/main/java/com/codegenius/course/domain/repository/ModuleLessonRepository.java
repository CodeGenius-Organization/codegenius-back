package com.codegenius.course.domain.repository;

import com.codegenius.course.domain.dto.ModuleLessonUpdateDTO;
import com.codegenius.course.domain.model.ModuleLessonModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ModuleLessonRepository extends JpaRepository<ModuleLessonModel, UUID> {

    List<ModuleLessonModel> findAllByModule_Id(UUID id);


    @Modifying
    @Transactional
    @Query("""
            UPDATE ModuleLessonModel ml SET ml.lessonOrder = ?2, ml.contentDescription = ?3, ml.lessonTitle = ?4
            WHERE ml.id = ?1
            """)
    void update(UUID lessonId, Integer lessonOrder, String contentDescription, String lessonTitle);
}

package com.codegenius.course.domain.repository;

import com.codegenius.course.domain.model.LessonContentModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LessonContentRepository extends JpaRepository<LessonContentModel, UUID> {

    LessonContentModel findByModuleLesson_Id(UUID moduleLessonId);

    @Query("UPDATE LessonContentModel lc SET lc.media = ?2 WHERE lc.id = ?1")
    @Modifying
    @Transactional
    void updateMedia(UUID lessonContentId, byte[] media);

    @Query("SELECT lc.media FROM LessonContentModel lc WHERE lc.id = ?1")
    byte[] getMedia(UUID lessonContentId);
}
package com.codegenius.course.domain.service;

import com.codegenius.course.domain.dto.LessonContentCreationDTO;
import com.codegenius.course.domain.model.LessonContentModel;
import com.codegenius.course.domain.model.ModuleLessonModel;
import com.codegenius.course.domain.repository.LessonContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class LessonContentService {
    @Autowired
    private LessonContentRepository lessonContentRepository;

    @Autowired
    private ModuleLessonService moduleLessonService;

    public LessonContentModel getLessonContentByLessonId(UUID moduleLessonId) {
//        ModuleLessonModel moduleLesson = moduleLessonService.getModuleLessonById(moduleLessonId);
        LessonContentModel lessonContent = lessonContentRepository.findByModuleLesson_Id(moduleLessonId);
        if (lessonContent == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lição do módulo não encontrada");
        }
        return lessonContent;
    }

    public LessonContentModel createLessonContent(LessonContentCreationDTO newLessonContent, UUID moduleLessonId) {
        ModuleLessonModel moduleLesson = moduleLessonService.getModuleLessonById(moduleLessonId);
        if (moduleLesson == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lição do módulo não encontrada");
        }
        LessonContentModel entity = new LessonContentModel();
        entity.setTitle(newLessonContent.getTitle());
        entity.setContent(newLessonContent.getContent());
        entity.setModuleLesson(moduleLesson);

        return lessonContentRepository.save(entity);
    }

    public byte[] updateMedia(UUID lessonContentId, byte[] media) {
        Optional<LessonContentModel> lessonContent = lessonContentRepository.findById(lessonContentId);
        if (lessonContent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conteúdo da lição não encontrado");
        }
        lessonContentRepository.updateMedia(lessonContentId, media);
        return media;
    }

    public byte[] getMedia(UUID lessonContentId) {
        Optional<LessonContentModel> lessonContent = lessonContentRepository.findById(lessonContentId);
        if (lessonContent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conteúdo da lição não encontrado");
        }
        return lessonContentRepository.getMedia(lessonContentId);
    }
}
package com.codegenius.course.controller;

import com.codegenius.course.domain.dto.LessonContentCreationDTO;
import com.codegenius.course.domain.model.LessonContentModel;
import com.codegenius.course.domain.service.LessonContentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:8181"})
@RequestMapping("/lessons-content")
public class LessonContentController {
    @Autowired
    private LessonContentService lessonContentService;

    @GetMapping("/{moduleLessonId}")
    public ResponseEntity<LessonContentModel> getLessonContentByLessonId(@PathVariable UUID moduleLessonId) {
        return ResponseEntity.status(200).body(lessonContentService.getLessonContentByLessonId(moduleLessonId));
    }

    @PostMapping("/{moduleLessonId}")
    public ResponseEntity<LessonContentModel> createLessonContent(@RequestBody LessonContentCreationDTO newLessonContent, @PathVariable UUID moduleLessonId) {
        return ResponseEntity.status(201).body(lessonContentService.createLessonContent(newLessonContent, moduleLessonId));
    }

    @GetMapping(value = "{lessonContentId}/image", produces = "image/**")
    public ResponseEntity<byte[]> getImage(@PathVariable UUID lessonContentId) {
        return ResponseEntity.status(200).body(lessonContentService.getMedia(lessonContentId));
    }

    @GetMapping(value = "{lessonContentId}/video", produces = "video/**")
    public ResponseEntity<byte[]> getVideo(@PathVariable UUID lessonContentId) {
        return ResponseEntity.status(200).body(lessonContentService.getMedia(lessonContentId));
    }

    @PatchMapping(value = "/{lessonContentId}/image", consumes = "image/**", produces = "image/**")
    public ResponseEntity<byte[]> updateImage(@PathVariable UUID lessonContentId, @RequestBody byte[] media) {
        return ResponseEntity.status(200).body(lessonContentService.updateMedia(lessonContentId, media));
    }

    @PatchMapping(value = "/{lessonContentId}/video", consumes = "video/**", produces = "video/**")
    public ResponseEntity<byte[]> updateVideo(@PathVariable UUID lessonContentId, @RequestBody byte[] media) {
        return ResponseEntity.status(200).body(lessonContentService.updateMedia(lessonContentId, media));
    }
}
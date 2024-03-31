package com.codegenius.feedback.controller;

import com.codegenius.feedback.domain.dto.DadosFeedbackCourse;
import com.codegenius.feedback.domain.dto.DadosFeedbackCourseCompleto;
import com.codegenius.feedback.domain.service.FeedbackCourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/feedbacks/courses")
@SecurityRequirement(name = "Bearer Authentication")
public class FeedbackCourseController {
    private final FeedbackCourseService feedbackService;

    @Autowired
    public FeedbackCourseController(FeedbackCourseService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<List<DadosFeedbackCourseCompleto>> getFeedbacksByCourseId(@PathVariable("courseId") UUID courseId) {
        List<DadosFeedbackCourseCompleto> list = feedbackService.findAllByCourseFk(courseId);
        return list.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(list);
    }

    @PostMapping("/save")
    public DadosFeedbackCourseCompleto saveFeedback(@RequestBody DadosFeedbackCourse dadosFeedbackCourse) {
        return feedbackService.saveFeedback(dadosFeedbackCourse);
    }
}

package com.codegenius.feedback.controller;

import com.codegenius.feedback.domain.dto.DadosFeedbackCourseCompleto;
import com.codegenius.feedback.domain.service.FeedbackCourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<DadosFeedbackCourseCompleto> getFeedbacksByCourseId(@PathVariable("courseId") UUID courseId) {
        return feedbackService.findAllByCourseFk(courseId);
    }
}

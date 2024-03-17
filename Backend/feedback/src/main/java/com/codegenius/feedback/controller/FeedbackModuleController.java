package com.codegenius.feedback.controller;

import com.codegenius.feedback.domain.dto.DadosFeedbackModuleCompleto;
import com.codegenius.feedback.domain.service.FeedbackModuleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/feedbacks/modules")
@SecurityRequirement(name = "Bearer Authentication")
public class FeedbackModuleController {
    private final FeedbackModuleService feedbackService;

    @Autowired
    public FeedbackModuleController(FeedbackModuleService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/{moduleId}")
    public List<DadosFeedbackModuleCompleto> getFeedbacksByModuleId(@PathVariable("moduleId") UUID moduleId) {
        return feedbackService.findAllByModuleFk(moduleId);
    }
}

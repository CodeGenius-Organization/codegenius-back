package com.codegenius.feedback.controller;

import com.codegenius.feedback.domain.dto.DadosFeedbackModule;
import com.codegenius.feedback.domain.dto.DadosFeedbackModuleCompleto;
import com.codegenius.feedback.domain.service.FeedbackModuleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<DadosFeedbackModuleCompleto>> getFeedbacksByModuleId(@PathVariable("moduleId") UUID moduleId) {
        List<DadosFeedbackModuleCompleto> list = feedbackService.findAllByModuleFk(moduleId);
        return list.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(list);
    }

    @PostMapping("/save")
    public DadosFeedbackModuleCompleto saveFeedback(@RequestBody DadosFeedbackModule dadosFeedbackModule) {
        return feedbackService.saveFeedback(dadosFeedbackModule);
    }
}

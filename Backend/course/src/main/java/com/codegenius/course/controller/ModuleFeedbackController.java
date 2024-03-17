package com.codegenius.course.controller;

import com.codegenius.course.domain.dto.ModuleFeedbackCreationDTO;
import com.codegenius.course.domain.model.ModuleFeedback;
import com.codegenius.course.domain.service.ModuleFeedbackService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
@RequestMapping("/feedbacks")
public class ModuleFeedbackController {
    @Autowired
    private ModuleFeedbackService service;

    @PostMapping("/")
    public ResponseEntity<ModuleFeedback> addFeedback(@RequestBody @Valid ModuleFeedbackCreationDTO data) {
        return ResponseEntity.status(200).body(service.addFeedback(data));
    }
}
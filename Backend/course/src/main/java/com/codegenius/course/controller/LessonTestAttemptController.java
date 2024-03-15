package com.codegenius.course.controller;

import com.codegenius.course.domain.dto.TestAttemptDTO;
import com.codegenius.course.domain.model.LessonTestAttempts;
import com.codegenius.course.domain.service.LessonTestAttemptService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:8181"})
@RequestMapping("/test-attempts")
public class LessonTestAttemptController {
    @Autowired
    private LessonTestAttemptService service;
    @GetMapping("/")
    public ResponseEntity<List<LessonTestAttempts>> getAllTestAttempts() {
        List<LessonTestAttempts> attempts = service.getAllTestAttempts();
        int status = attempts.isEmpty() ? 204 : 200;
        return ResponseEntity.status(status).body(attempts);
    }

    @PostMapping("/")
    public ResponseEntity<LessonTestAttempts> addNewAttempt(@RequestBody TestAttemptDTO data) {
        return ResponseEntity.status(201).body(service.addNewAttempt(data));
    }
}
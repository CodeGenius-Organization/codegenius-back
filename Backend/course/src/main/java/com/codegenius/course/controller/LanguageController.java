package com.codegenius.course.controller;

import com.codegenius.course.domain.model.LanguageModel;
import com.codegenius.course.domain.service.LanguageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
@RequestMapping("/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @PostMapping("/{language}")
    public ResponseEntity<LanguageModel> createLanguage(@PathVariable String language) {
        LanguageModel newLanguage = new LanguageModel(language);
        return ResponseEntity.status(201).body(this.languageService.createLanguage(newLanguage));
    }

    @GetMapping("/")
    public ResponseEntity<List<LanguageModel>> getLanguages() {
        List<LanguageModel> languages = languageService.getLanguages();
        if (languages.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(languages);
    }


    @GetMapping("/{name}")
    public ResponseEntity<LanguageModel> getLanguages(@PathVariable String name) {
        LanguageModel languages = languageService.findLanguageByName(name);
        return ResponseEntity.status(200).body(languages);
    }
}
package com.codegenius.course.domain.service;

import com.codegenius.course.domain.model.LanguageModel;
import com.codegenius.course.domain.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public LanguageModel createLanguage(LanguageModel language) {
        return this.languageRepository.save(language);
    }

    public List<LanguageModel> getLanguages() {
        return this.languageRepository.findAll();
    }

    public List<LanguageModel> findLanguageByIdIn(List<UUID> ids) {
        List<LanguageModel> languages = this.languageRepository.findLanguageByIdIn(ids);

        for (LanguageModel l : languages) {
            if (!ids.contains(l.getId())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Linguagem de id " + l.getId() + " não existe.");
            }
        }

        return languages;
    }

    public LanguageModel findLanguageById(UUID languageId) {
        Optional<LanguageModel> language = languageRepository.findById(languageId);
        if (language.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Linguagem de id " + languageId + " não encontrada.");
        }
        return language.get();
    }

    public LanguageModel findLanguageByName(String name) {
        Optional<LanguageModel> language = languageRepository.findLanguageByLanguage(name);
        if (language.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Linguagem de nome " + name + " não encontrado.");
        }
        return language.get();
    }
}
package com.codegenius.achievement.domain.service;

import com.codegenius.achievement.domain.dto.PointsRegisterDTO;
import com.codegenius.achievement.domain.model.UserCategoryPointsModel;
import com.codegenius.achievement.domain.model.UserLanguagePointsModel;
import com.codegenius.achievement.domain.repository.CategoryPointsRepository;
import com.codegenius.achievement.domain.repository.LanguagePointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PointsService {
    @Autowired
    private CategoryPointsRepository categoryRepository;
    @Autowired
    private LanguagePointsRepository languageRepository;


    public void registerPoints(UUID userId, PointsRegisterDTO data) {
        for (UUID category : data.getCategories()) {
            if (category != null) {
                UserCategoryPointsModel adsuofihadsf = new UserCategoryPointsModel(null, data.getPoints(), category, userId);
                Boolean isAlreadyRegistered = categoryRepository.existsByCategoryFkAndUserFk(category, userId);
                if (!isAlreadyRegistered) {
                    categoryRepository.save(adsuofihadsf);
                }
            }
        }

        for (UUID language : data.getLanguages()) {
            if (language != null) {
                UserLanguagePointsModel jk = new UserLanguagePointsModel(null, data.getPoints(), language, userId);
                Boolean isAlreadyRegistered = languageRepository.existsByLanguageFkAndUserFk(language, userId);
                if (!isAlreadyRegistered) {
                    languageRepository.save(jk);
                }
            }
        }
    }

    public void updatePoints(UUID userId, PointsRegisterDTO data) {
        for (UUID category : data.getCategories()) {
            if (category != null) {
                categoryRepository.updatePoints(category, data.getPoints(), userId);
            }
        }

        for (UUID language : data.getLanguages()) {
            if (language != null) {
                languageRepository.updatePoints(language, data.getPoints(), userId);
            }
        }
    }
}

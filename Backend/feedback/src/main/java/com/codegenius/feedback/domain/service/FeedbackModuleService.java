package com.codegenius.feedback.domain.service;

import com.codegenius.feedback.domain.dto.DadosFeedbackModuleCompleto;
import com.codegenius.feedback.domain.model.FeedbackModuleModel;
import com.codegenius.feedback.domain.repository.FeedbackModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FeedbackModuleService {
    private final FeedbackModuleRepository feedbackRepository;

    @Autowired
    public FeedbackModuleService(FeedbackModuleRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<DadosFeedbackModuleCompleto> findAllByModuleFk(UUID courseFk) {
        List<FeedbackModuleModel> feedbackList = feedbackRepository.findAllByModuleFk(courseFk);
        return feedbackList.stream()
                .map(this::mapToDadosFeedbackModuleCompleto)
                .collect(Collectors.toList());
    }

    private DadosFeedbackModuleCompleto mapToDadosFeedbackModuleCompleto(FeedbackModuleModel feedback) {
        DadosFeedbackModuleCompleto dto = new DadosFeedbackModuleCompleto();
        dto.setId(feedback.getId());
        dto.setStars(feedback.getStars());
        dto.setFeedback(feedback.getFeedback());
        dto.setFeedbackDate(feedback.getFeedbackDate());
        dto.setModuleFk(feedback.getModuleFk());
        dto.setUserFk(feedback.getUserFk());
        return dto;
    }
}

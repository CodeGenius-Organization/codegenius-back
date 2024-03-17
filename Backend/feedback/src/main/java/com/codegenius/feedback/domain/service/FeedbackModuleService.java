package com.codegenius.feedback.domain.service;

import com.codegenius.feedback.domain.dto.DadosFeedbackModule;
import com.codegenius.feedback.domain.dto.DadosFeedbackModuleCompleto;
import com.codegenius.feedback.domain.model.FeedbackModuleModel;
import com.codegenius.feedback.domain.repository.FeedbackModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

    public DadosFeedbackModuleCompleto saveFeedback(DadosFeedbackModule dadosFeedbackModule) {
        FeedbackModuleModel feedbackModel = new FeedbackModuleModel();
        feedbackModel.setStars(dadosFeedbackModule.getStars());
        feedbackModel.setFeedback(dadosFeedbackModule.getFeedback());
        LocalDate localDate = LocalDate.now();
        Date feedbackDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        feedbackModel.setFeedbackDate(feedbackDate);
        feedbackModel.setModuleFk(dadosFeedbackModule.getModuleFk());
        feedbackModel.setUserFk(dadosFeedbackModule.getUserFk());

        FeedbackModuleModel savedFeedback = feedbackRepository.save(feedbackModel);

        DadosFeedbackModuleCompleto completo = new DadosFeedbackModuleCompleto();
        completo.setId(savedFeedback.getId());
        completo.setStars(savedFeedback.getStars());
        completo.setFeedback(savedFeedback.getFeedback());
        completo.setFeedbackDate(savedFeedback.getFeedbackDate());
        completo.setModuleFk(savedFeedback.getModuleFk());
        completo.setUserFk(savedFeedback.getUserFk());

        return completo;
    }
}

package com.codegenius.feedback.domain.service;

import com.codegenius.feedback.domain.dto.DadosFeedbackCourse;
import com.codegenius.feedback.domain.dto.DadosFeedbackCourseCompleto;
import com.codegenius.feedback.domain.dto.DadosFeedbackModule;
import com.codegenius.feedback.domain.dto.DadosFeedbackModuleCompleto;
import com.codegenius.feedback.domain.model.FeedbackCourseModel;
import com.codegenius.feedback.domain.model.FeedbackModuleModel;
import com.codegenius.feedback.domain.repository.FeedbackCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FeedbackCourseService {
    private final FeedbackCourseRepository feedbackRepository;

    @Autowired
    public FeedbackCourseService(FeedbackCourseRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<DadosFeedbackCourseCompleto> findAllByCourseFk(UUID courseFk) {
        List<FeedbackCourseModel> feedbackList = feedbackRepository.findAllByCourseFk(courseFk);
        return feedbackList.stream()
                .map(this::mapToDadosFeedbackCourseCompleto)
                .collect(Collectors.toList());
    }

    private DadosFeedbackCourseCompleto mapToDadosFeedbackCourseCompleto(FeedbackCourseModel feedback) {
        DadosFeedbackCourseCompleto dto = new DadosFeedbackCourseCompleto();
        dto.setId(feedback.getId());
        dto.setFeedback(feedback.getFeedback());
        dto.setFeedbackDescription(feedback.getFeedbackDescription());
        dto.setFeedbackDate(feedback.getFeedbackDate());
        dto.setCourseFk(feedback.getCourseFk());
        dto.setUserFk(feedback.getUserFk());
        return dto;
    }

    public DadosFeedbackCourseCompleto saveFeedback(DadosFeedbackCourse dadosFeedbackCourse) {
        FeedbackCourseModel feedbackModel = new FeedbackCourseModel();
        feedbackModel.setFeedback(dadosFeedbackCourse.getFeedback());
        feedbackModel.setFeedbackDescription(dadosFeedbackCourse.getFeedbackDescription());
        LocalDate localDate = LocalDate.now();
        Date feedbackDate = Date.valueOf(localDate);
        feedbackModel.setFeedbackDate(feedbackDate);
        feedbackModel.setCourseFk(dadosFeedbackCourse.getCourseFk());
        feedbackModel.setUserFk(dadosFeedbackCourse.getUserFk());

        FeedbackCourseModel savedFeedback = feedbackRepository.save(feedbackModel);

        DadosFeedbackCourseCompleto completo = new DadosFeedbackCourseCompleto();
        completo.setId(savedFeedback.getId());
        completo.setFeedback(savedFeedback.getFeedback());
        completo.setFeedbackDescription(savedFeedback.getFeedbackDescription());
        completo.setFeedbackDate(savedFeedback.getFeedbackDate());
        completo.setCourseFk(savedFeedback.getCourseFk());
        completo.setUserFk(savedFeedback.getUserFk());

        return completo;
    }
}

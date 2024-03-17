package com.codegenius.feedback.domain.service;

import com.codegenius.feedback.domain.dto.DadosFeedbackCourseCompleto;
import com.codegenius.feedback.domain.model.FeedbackCourseModel;
import com.codegenius.feedback.domain.repository.FeedbackCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

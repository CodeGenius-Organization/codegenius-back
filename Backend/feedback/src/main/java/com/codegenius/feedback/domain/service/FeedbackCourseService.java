package com.codegenius.feedback.domain.service;

import com.codegenius.feedback.domain.dto.*;
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

    public List<CourseFeedbackSimple> findAllByCourseFk(UUID courseFk) {
        List<FeedbackCourseModel> feedbackList = feedbackRepository.findAllByCourseFkOrderByStarsAsc(courseFk);
        return feedbackList.stream()
                .map(this::mapToCourseFeedbackSimple)
                .collect(Collectors.toList());
    }

    private CourseFeedbackSimple mapToCourseFeedbackSimple(FeedbackCourseModel feedback) {
        CourseFeedbackSimple dto = new CourseFeedbackSimple();
        dto.setId(feedback.getId());
        dto.setRate(feedback.getStars());
        dto.setFeedbackDescription(feedback.getFeedback());
        return dto;
    }

    private DadosFeedbackCourseCompleto mapToDadosFeedbackCourseCompleto(FeedbackCourseModel feedback) {
        DadosFeedbackCourseCompleto dto = new DadosFeedbackCourseCompleto();
        dto.setId(feedback.getId());
        dto.setStars(feedback.getStars());
        dto.setFeedback(feedback.getFeedback());
        dto.setFeedbackDate(feedback.getFeedbackDate());
        dto.setCourseFk(feedback.getCourseFk());
        dto.setUserFk(feedback.getUserFk());
        return dto;
    }

    public DadosFeedbackCourseCompleto saveFeedback(DadosFeedbackCourse dadosFeedbackCourse) {
        FeedbackCourseModel feedbackModel = new FeedbackCourseModel();
        feedbackModel.setStars(dadosFeedbackCourse.getStars());
        feedbackModel.setFeedback(dadosFeedbackCourse.getFeedback());
        LocalDate localDate = LocalDate.now();
        Date feedbackDate = Date.valueOf(localDate);
        feedbackModel.setFeedbackDate(feedbackDate);
        feedbackModel.setCourseFk(dadosFeedbackCourse.getCourseFk());
        feedbackModel.setUserFk(dadosFeedbackCourse.getUserFk());

        FeedbackCourseModel savedFeedback = feedbackRepository.save(feedbackModel);

        DadosFeedbackCourseCompleto completo = new DadosFeedbackCourseCompleto();
        completo.setId(savedFeedback.getId());
        completo.setStars(savedFeedback.getStars());
        completo.setFeedback(savedFeedback.getFeedback());
        completo.setFeedbackDate(savedFeedback.getFeedbackDate());
        completo.setCourseFk(savedFeedback.getCourseFk());
        completo.setUserFk(savedFeedback.getUserFk());

        return completo;
    }
}

package com.codegenius.course.domain.service;

import com.codegenius.course.domain.dto.TestAttemptDTO;
import com.codegenius.course.domain.dto.TestAttemptMapper;
import com.codegenius.course.domain.model.LessonTestAttempts;
import com.codegenius.course.domain.model.ModuleLessonModel;
import com.codegenius.course.domain.model.UserModel;
import com.codegenius.course.domain.repository.LessonTestAttemptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonTestAttemptService {
    @Autowired
    private LessonTestAttemptsRepository repository;

    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    private ModuleLessonService moduleLessonService;

    public LessonTestAttempts addNewAttempt(TestAttemptDTO data) {
        UserModel user = userService.getUserById(data.getUserId());
        ModuleLessonModel moduleLesson = moduleLessonService.getModuleLessonById(data.getModuleLessonId());
        LessonTestAttempts newAttempt = TestAttemptMapper.of(data, user, moduleLesson);
        return repository.save(newAttempt);
    }

    public List<LessonTestAttempts> getAllTestAttempts() {
        return repository.findAll();
    }
}

package com.codegenius.course.domain.service;

import com.codegenius.course.domain.dto.ModuleFeedbackCreationDTO;
import com.codegenius.course.domain.dto.ModuleFeedbackMapper;
import com.codegenius.course.domain.model.CourseModuleModel;
import com.codegenius.course.domain.model.ModuleFeedback;
import com.codegenius.course.domain.model.UserModel;
import com.codegenius.course.domain.repository.ModuleFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleFeedbackService {
    @Autowired
    private ModuleFeedbackRepository repository;
    @Autowired
    private CourseModuleService moduleService;
    @Autowired
    private CustomUserDetailsService userService;

    public ModuleFeedback addFeedback(ModuleFeedbackCreationDTO data) {
        UserModel user = userService.getUserById(data.getUserFk());
        CourseModuleModel module = moduleService.getCourseModuleById(data.getModuleFk());
        ModuleFeedback feedback = ModuleFeedbackMapper.of(data, user, module);
        return repository.save(feedback);
    }
}

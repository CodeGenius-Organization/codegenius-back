package com.codegenius.course.domain.service;

import com.codegenius.course.domain.dto.CourseModuleCreationDTO;
import com.codegenius.course.domain.dto.CourseModuleMapper;
import com.codegenius.course.domain.model.CourseModel;
import com.codegenius.course.domain.model.CourseModuleModel;
import com.codegenius.course.domain.repository.CourseModuleRepository;
import com.codegenius.course.domain.repository.CourseRepository;
import com.codegenius.course.utils.ListaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseModuleService {

    @Autowired
    private CourseModuleRepository courseModuleRepository;

    @Autowired
    private CourseRepository courseRepository;

    public CourseModuleModel createCourseModule(CourseModuleCreationDTO courseModuleCreationDTO) {
        Optional<CourseModel> course = courseRepository.findById(courseModuleCreationDTO.getCourseId());

        if (course.isEmpty()) {
            throw new IllegalStateException();
        }

        final CourseModuleModel courseModule = CourseModuleMapper.of(courseModuleCreationDTO, course.get());

        return this.courseModuleRepository.save(courseModule);
    }

    public List<CourseModuleModel> getModulesByCourseIdOrdered(UUID courseId) {
//        List<CourseModuleModel> modules = courseModuleRepository.findAllByCourse_Id(courseId);
//        ListaObj<CourseModuleModel> modulesOrdered = new ListaObj<>(modules.size());
//
//        // Definição dos elementos da ListaObj
//        for (CourseModuleModel module : modules) {
//            modulesOrdered.adiciona(module);
//        }
//
//        // Ordenação
//        for (int i = 0; i < modulesOrdered.getTamanho() - 1; )


        return this.courseModuleRepository.findAllByCourse_IdOrderByModuleOrderAsc(courseId);
    }

    public CourseModuleModel updateModuleName(UUID courseModuleId, String moduleName) {
        Optional<CourseModuleModel> courseModule = this.courseModuleRepository.findById(courseModuleId);

        if (courseModule.isEmpty()) {
            throw new IllegalStateException();
        }

        courseModule.get().setModuleName(moduleName);
        return this.courseModuleRepository.save(courseModule.get());
    }

    public CourseModuleModel updateModuleOrder(UUID courseModuleId, Integer moduleOrder) {
        Optional<CourseModuleModel> courseModule = this.courseModuleRepository.findById(courseModuleId);

        if (courseModule.isEmpty()) {
            throw new IllegalStateException();
        }

        courseModule.get().setModuleOrder(moduleOrder);
        return this.courseModuleRepository.save(courseModule.get());
    }

    public void deleteCourseModule(UUID courseModuleId) {
        Optional<CourseModuleModel> courseModule = this.courseModuleRepository.findById(courseModuleId);

        if (courseModule.isEmpty()) {
            throw new IllegalStateException();
        }

        this.courseModuleRepository.delete(courseModule.get());
    }

    public CourseModuleModel getCourseModuleById(UUID courseModuleId) {
        return this.courseModuleRepository.findById(courseModuleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course module not found"));
    }
}
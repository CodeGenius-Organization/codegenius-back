package com.codegenius.course.controller;

import com.codegenius.course.domain.dto.CourseModuleCreationDTO;
import com.codegenius.course.domain.model.CourseModuleModel;
import com.codegenius.course.domain.service.CourseModuleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
@RequestMapping("/modules")
public class CourseModuleController {

    @Autowired
    private CourseModuleService courseModuleService;

    @PostMapping("/")
    public ResponseEntity<CourseModuleModel> createCourseModule(@RequestBody @Valid CourseModuleCreationDTO courseModuleCreationDTO) {
        return ResponseEntity.status(201).body(courseModuleService.createCourseModule(courseModuleCreationDTO));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<List<CourseModuleModel>> getModulesByCourseIdOrdered(@PathVariable UUID courseId) {
        return ResponseEntity.status(200).body(courseModuleService.getModulesByCourseIdOrdered(courseId));
    }

    @PatchMapping("/name/{courseModuleId}/{moduleName}")
    public ResponseEntity<CourseModuleModel> updateModuleName(@PathVariable UUID courseModuleId, @PathVariable String moduleName) {
        return ResponseEntity.status(200).body(courseModuleService.updateModuleName(courseModuleId, moduleName));
    }

    @PatchMapping("/order/{courseModuleId}/{moduleOrder}")
    public ResponseEntity<CourseModuleModel> updateModuleOrder(@PathVariable UUID courseModuleId, @PathVariable Integer moduleOrder) {
        return ResponseEntity.status(200).body(courseModuleService.updateModuleOrder(courseModuleId, moduleOrder));
    }

    @DeleteMapping("/{courseModuleId}")
    public ResponseEntity<Void> deleteCourseModule(@PathVariable UUID courseModuleId) {
        this.courseModuleService.deleteCourseModule(courseModuleId);
        return ResponseEntity.status(200).build();
    }
}
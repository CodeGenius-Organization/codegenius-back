package com.codegenius.achievement.controller;

import com.codegenius.achievement.domain.dto.PointsRegisterDTO;
import com.codegenius.achievement.domain.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/points")
public class PointsController {
    @Autowired
    private PointsService service;

    @PostMapping("/{userId}")
    private ResponseEntity registerPoints(@PathVariable UUID userId, @RequestBody PointsRegisterDTO data) {
        service.registerPoints(userId, data);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/{userId}")
    private ResponseEntity updatePoints(@PathVariable UUID userId, @RequestBody PointsRegisterDTO data) {
        service.updatePoints(userId, data);
        return ResponseEntity.status(200).build();
    }
}
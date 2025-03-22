package com.brainnotfound.employeeassessmentbe.controllers;

import com.brainnotfound.employeeassessmentbe.DTO.AssessmentDto;
import com.brainnotfound.employeeassessmentbe.models.Assessment;
import com.brainnotfound.employeeassessmentbe.services.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {
    @Autowired
    private AssessmentService assessmentService;

    @PostMapping
    public ResponseEntity<Assessment> createAssessment(@RequestBody AssessmentDto dto) {
        Assessment assessment = assessmentService.createAssessment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(assessment);
    }
}

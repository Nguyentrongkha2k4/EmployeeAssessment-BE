package com.brainnotfound.employeeassessmentbe.controllers;

import com.brainnotfound.employeeassessmentbe.DTO.AssessmentDto;
import com.brainnotfound.employeeassessmentbe.DTO.ResponseObject;
import com.brainnotfound.employeeassessmentbe.services.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {
    @Autowired
    private AssessmentService assessmentService;

    @PostMapping
    public ResponseObject<AssessmentDto> createAssessment(@RequestBody AssessmentDto dto) {
        return ResponseObject.<AssessmentDto>builder()
                .status(201)
                .message("Created")
                .data(assessmentService.createAssessment(dto))
                .build();
    }

    @GetMapping("/all")
    public ResponseObject<List<AssessmentDto>> getAllAssessments() {
        return ResponseObject.<List<AssessmentDto>>builder()
                .status(200)
                .message("Success")
                .data(assessmentService.getAllAssessments())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseObject<AssessmentDto> getAssessmentById(@PathVariable Long id) {
        return ResponseObject.<AssessmentDto>builder()
                .status(200)
                .message("Success")
                .data(assessmentService.getAssessment(id))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseObject<AssessmentDto> updateAssessment(@PathVariable Long id, @RequestBody AssessmentDto dto) {
        return ResponseObject.<AssessmentDto>builder()
                .status(200)
                .message("Updated")
                .data(assessmentService.updateAssessment(id, dto))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseObject<Void> deleteAssessment(@PathVariable Long id) {
        assessmentService.deleteAssessment(id);
        return ResponseObject.<Void>builder()
                .status(204)
                .message("Deleted")
                .build();
    }

}

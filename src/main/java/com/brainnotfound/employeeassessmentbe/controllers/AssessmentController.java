package com.brainnotfound.employeeassessmentbe.controllers;

import com.brainnotfound.employeeassessmentbe.DTO.AssessmentDto;
import com.brainnotfound.employeeassessmentbe.DTO.ResponseObject;
import com.brainnotfound.employeeassessmentbe.DTO.request.AssessmentReq;
import com.brainnotfound.employeeassessmentbe.models.User;
import com.brainnotfound.employeeassessmentbe.services.AssessmentService;
import com.brainnotfound.employeeassessmentbe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
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

    @GetMapping("/my/feedback")
    public ResponseObject<List<String>> getMyFeedback() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        long userIdLong = Long.parseLong(userId);
        return ResponseObject.<List<String>>builder()
                .status(200)
                .message("Success")
                .data(assessmentService.getMyFeedback(userIdLong))
                .build();
    }
    @PostMapping("/my/feedback")
    public ResponseObject<String> postMyFeedback(@RequestBody AssessmentReq req) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        long userIdLong = Long.parseLong(userId);
        return ResponseObject.<String>builder()
                .status(201)
                .message("Success created")
                .data(assessmentService.postMyFeedback(userIdLong, req))
                .build();
    }
    @PutMapping("/my/feedback{assessId}")
    public ResponseObject<String> updateMyFeedback(@PathVariable long assessId, @RequestBody AssessmentReq req) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        long userIdLong = Long.parseLong(userId);
        return ResponseObject.<String>builder()
                .status(200)
                .message("Success updated")
                .data(assessmentService.updateMyFeedback(assessId, userIdLong, req))
                .build();
    }
    @DeleteMapping("/my/feedback{assessId}")
    public ResponseObject<String> deleteMyFeedback(@PathVariable long assessId) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        long userIdLong = Long.parseLong(userId);
        assessmentService.deleteMyFeedback(assessId, userIdLong);
        return ResponseObject.<String>builder()
                .status(204)
                .message("Deleted")
                .build();
    }

}

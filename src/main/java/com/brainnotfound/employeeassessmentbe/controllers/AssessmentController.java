package com.brainnotfound.employeeassessmentbe.controllers;

import com.brainnotfound.employeeassessmentbe.DTO.AssessmentDto;
import com.brainnotfound.employeeassessmentbe.DTO.ResponseObject;
import com.brainnotfound.employeeassessmentbe.services.AssessmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {
    @Autowired
    private AssessmentService assessmentService;

    @Operation(summary = "Create assessment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @PostMapping
    public ResponseObject<AssessmentDto> createAssessment(@RequestBody AssessmentDto dto) {
        return ResponseObject.<AssessmentDto>builder()
                .status(201)
                .message("Created")
                .data(assessmentService.createAssessment(dto))
                .build();
    }

    @Operation(summary = "Get all assessments")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @GetMapping("/all")
    public ResponseObject<List<AssessmentDto>> getAllAssessments() {
        return ResponseObject.<List<AssessmentDto>>builder()
                .status(200)
                .message("Success")
                .data(assessmentService.getAllAssessments())
                .build();
    }


    @Operation(summary = "Get assessment by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @GetMapping("/{id}")
    public ResponseObject<AssessmentDto> getAssessmentById(@PathVariable Long id) {
        return ResponseObject.<AssessmentDto>builder()
                .status(200)
                .message("Success")
                .data(assessmentService.getAssessment(id))
                .build();
    }

    @Operation(summary = "Update assessment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @PutMapping("/{id}")
    public ResponseObject<AssessmentDto> updateAssessment(@PathVariable Long id, @RequestBody AssessmentDto dto) {
        return ResponseObject.<AssessmentDto>builder()
                .status(200)
                .message("Updated")
                .data(assessmentService.updateAssessment(id, dto))
                .build();
    }

    @Operation(summary = "Delete assessment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Deleted"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @DeleteMapping("/{id}")
    public ResponseObject<Void> deleteAssessment(@PathVariable Long id) {
        assessmentService.deleteAssessment(id);
        return ResponseObject.<Void>builder()
                .status(204)
                .message("Deleted")
                .build();
    }

    @Operation(summary = "Get my assessments")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @GetMapping("/me")
    public ResponseObject<List<AssessmentDto>> getMyAssessments() {
        var userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        return ResponseObject.<List<AssessmentDto>>builder()
                .status(200)
                .message("Success")
                .data(assessmentService.getMyAssessments(userId))
                .build();
    }

}

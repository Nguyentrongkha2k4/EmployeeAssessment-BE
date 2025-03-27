package com.brainnotfound.employeeassessmentbe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brainnotfound.employeeassessmentbe.DTO.AssessmentDto;
import com.brainnotfound.employeeassessmentbe.DTO.ResponseObject;
import com.brainnotfound.employeeassessmentbe.DTO.response.AssessmentList;
import com.brainnotfound.employeeassessmentbe.services.AssessmentService;


@RestController
@RequestMapping("/assessment")
public class AssessmentController {
    @Autowired
    private AssessmentService assessmentService;

    @PostMapping
    public ResponseEntity<AssessmentDto> createAssessment(@RequestBody AssessmentDto dto) {
        AssessmentDto assessmentDto = assessmentService.createAssessment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(assessmentDto);
    }

    @GetMapping
    public ResponseEntity<List<AssessmentDto>> getAllAssessments() {
        return new ResponseEntity<>(assessmentService.getAllAssessments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssessmentDto> getAssessmentById(@PathVariable Long id) {
        return new ResponseEntity<>(assessmentService.getAssessmentById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssessment(@PathVariable Long id) {
        assessmentService.deleteAssessment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/supervisee")
    public ResponseObject<List<AssessmentList>> getMethodName() {
        ResponseObject<List<AssessmentList>> responseObject = ResponseObject.<List<AssessmentList>>builder()
                                                                                    .status(200)
                                                                                    .data(assessmentService.getSuperviseeAssessment())
                                                                                    .build();
        return responseObject;
    }
    

}

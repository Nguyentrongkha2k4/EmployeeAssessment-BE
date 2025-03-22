package com.brainnotfound.employeeassessmentbe.controllers;

import com.brainnotfound.employeeassessmentbe.models.Criteria;
import com.brainnotfound.employeeassessmentbe.repositories.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/criteria")
public class CriteriaController {

    @Autowired
    private CriteriaRepository criteriaRepository;

    @PostMapping
    public ResponseEntity<Criteria> createCriteria(@RequestBody Criteria criteria) {
        Criteria saved = criteriaRepository.save(criteria);
        return ResponseEntity.status(201).body(saved);
    }
}

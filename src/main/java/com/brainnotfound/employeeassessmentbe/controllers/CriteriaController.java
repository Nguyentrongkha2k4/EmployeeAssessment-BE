package com.brainnotfound.employeeassessmentbe.controllers;

import com.brainnotfound.employeeassessmentbe.models.Criteria;
import com.brainnotfound.employeeassessmentbe.repositories.CriteriaRepository;
import com.brainnotfound.employeeassessmentbe.services.CriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/criteria")
public class CriteriaController {

    @Autowired
    private CriteriaService criteriaService;

    @PostMapping
    public ResponseEntity<Criteria> createCriteria(@RequestBody Criteria criteria) {
        return ResponseEntity.status(201).body(criteriaService.save(criteria));
    }
    @GetMapping
    public ResponseEntity<List<Criteria>> getCriteria() {
        return ResponseEntity.status(200).body(criteriaService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Criteria> getCriteriaById(@PathVariable int id) {
        return ResponseEntity.status(200).body(criteriaService.findById(id));
    }
}

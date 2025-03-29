package com.brainnotfound.employeeassessmentbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brainnotfound.employeeassessmentbe.models.Criteria;

public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
    boolean existsByName(String name);
    
}

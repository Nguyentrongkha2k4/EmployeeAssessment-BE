package com.brainnotfound.employeeassessmentbe.services;

import com.brainnotfound.employeeassessmentbe.models.Criteria;
import com.brainnotfound.employeeassessmentbe.repositories.AssessmentRepository;
import com.brainnotfound.employeeassessmentbe.repositories.CriteriaRepository;
import com.brainnotfound.employeeassessmentbe.repositories.UserRepository;

public class CriteriaService {
    private CriteriaRepository criteriaRepository;
    public Criteria save (Criteria criteria) {
        return criteriaRepository.save(criteria);
    }
}

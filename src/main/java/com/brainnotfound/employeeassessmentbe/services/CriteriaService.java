package com.brainnotfound.employeeassessmentbe.services;

import com.brainnotfound.employeeassessmentbe.models.Criteria;
import com.brainnotfound.employeeassessmentbe.repositories.AssessmentRepository;
import com.brainnotfound.employeeassessmentbe.repositories.CriteriaRepository;
import com.brainnotfound.employeeassessmentbe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CriteriaService {
    @Autowired
    private CriteriaRepository criteriaRepository;

    public Criteria save (Criteria criteria) {
        return criteriaRepository.save(criteria);
    }
    public List<Criteria> findAll() {
        return criteriaRepository.findAll();
    }
    public Criteria findById(Long id) {
        return criteriaRepository.findById(id).orElse(null);
    }
}

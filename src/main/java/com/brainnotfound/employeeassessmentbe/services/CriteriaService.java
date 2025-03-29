package com.brainnotfound.employeeassessmentbe.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainnotfound.employeeassessmentbe.DTO.request.CriteriaRequest;
import com.brainnotfound.employeeassessmentbe.exception.AppException;
import com.brainnotfound.employeeassessmentbe.exception.ErrorCode;
import com.brainnotfound.employeeassessmentbe.models.Criteria;
import com.brainnotfound.employeeassessmentbe.repositories.CriteriaRepository;
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

    public Criteria create(CriteriaRequest request){
        if (criteriaRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.CRITERIA_EXISTS);
        }
        Criteria criteria = Criteria.builder().name(request.getName()).description(request.getDescription()).build();

        return criteriaRepository.save(criteria);
    }
}

package com.brainnotfound.employeeassessmentbe.services;

import com.brainnotfound.employeeassessmentbe.DTO.request.CriteriaReq;
import com.brainnotfound.employeeassessmentbe.exception.AppException;
import com.brainnotfound.employeeassessmentbe.exception.ErrorCode;
import com.brainnotfound.employeeassessmentbe.models.Criteria;
import com.brainnotfound.employeeassessmentbe.repositories.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CriteriaService {
    @Autowired
    private CriteriaRepository criteriaRepository;

    public Criteria create(CriteriaReq criteriaReq) {
        Criteria criteria = Criteria.builder()
                .name(criteriaReq.getName())
                .description(criteriaReq.getDescription())
                .build();
        return criteriaRepository.save(criteria);
    }
    public List<Criteria> findAll() {
        return criteriaRepository.findAll();
    }
    public Criteria findById(Long id) {
        return criteriaRepository.findById(id).orElse(null);
    }
    public Criteria update(Long id, CriteriaReq criteriaReq){
        Criteria existingCriteria = criteriaRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CRITERIA_NOT_EXISTED));
        existingCriteria.setName(criteriaReq.getName());
        existingCriteria.setDescription(criteriaReq.getDescription());
        return criteriaRepository.save(existingCriteria);
    }
    public void delete(Long id) {
        Criteria criteria = criteriaRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CRITERIA_NOT_EXISTED));
        criteriaRepository.delete(criteria);
    }
}

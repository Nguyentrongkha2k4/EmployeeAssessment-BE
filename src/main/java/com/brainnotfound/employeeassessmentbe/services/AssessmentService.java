package com.brainnotfound.employeeassessmentbe.services;

import com.brainnotfound.employeeassessmentbe.DTO.AssessmentDto;
import com.brainnotfound.employeeassessmentbe.controllers.AssessmentController;
import com.brainnotfound.employeeassessmentbe.models.Assessment;
import com.brainnotfound.employeeassessmentbe.models.Criteria;
import com.brainnotfound.employeeassessmentbe.models.User;
import com.brainnotfound.employeeassessmentbe.repositories.AssessmentRepository;
import com.brainnotfound.employeeassessmentbe.repositories.CriteriaRepository;
import com.brainnotfound.employeeassessmentbe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssessmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CriteriaRepository criteriaRepository;

    @Autowired
    private AssessmentRepository assessmentRepository;

    public Assessment createAssessment(AssessmentDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Criteria criteria = criteriaRepository.findById(dto.getCriteriaId())
                .orElseThrow(() -> new RuntimeException("Criteria not found"));

        Assessment assessment = new Assessment();
        assessment.setUser(user);
        assessment.setCriteria(criteria);
        assessment.setScore(dto.getScore());
        assessment.setComment(dto.getComment());

        return assessmentRepository.save(assessment);
    }
}
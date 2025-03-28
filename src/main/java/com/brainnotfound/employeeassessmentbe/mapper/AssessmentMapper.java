package com.brainnotfound.employeeassessmentbe.mapper;

import com.brainnotfound.employeeassessmentbe.DTO.response.AssessmentResponse;
import com.brainnotfound.employeeassessmentbe.models.Assessment;
import org.springframework.stereotype.Component;

@Component
public class AssessmentMapper {

    public AssessmentResponse toAssessmentDto(Assessment assessment) {
        return new AssessmentResponse(assessment.getId(), assessment.getUser().getId(), assessment.getCriteria().getId(), assessment.getScore(), assessment.getComment());
    }
}

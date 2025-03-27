package com.brainnotfound.employeeassessmentbe.mapper;

import com.brainnotfound.employeeassessmentbe.DTO.AssessmentDto;
import com.brainnotfound.employeeassessmentbe.models.Assessment;
import org.springframework.stereotype.Component;

@Component
public class AssessmentMapper {

    public AssessmentDto toAssessmentDto(Assessment assessment) {
        return new AssessmentDto(assessment.getUser().getId(), assessment.getCriteria().getId(), assessment.getScore(), assessment.getComment());
    }
}

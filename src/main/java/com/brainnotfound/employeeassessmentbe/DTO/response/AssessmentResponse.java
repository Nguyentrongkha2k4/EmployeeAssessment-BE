package com.brainnotfound.employeeassessmentbe.DTO.response;

import com.brainnotfound.employeeassessmentbe.models.Assessment;
import lombok.Data;

@Data
public class AssessmentResponse {

    private Long userId;
    private Long criteriaId;
    private Integer score;
    private String comment;

    public AssessmentResponse() {
    }

    public AssessmentResponse(Long userId, Long criteriaId, Integer score, String comment) {
        this.userId = userId;
        this.criteriaId = criteriaId;
        this.score = score;
        this.comment = comment;
    }

    public AssessmentResponse(Assessment assessment) {
        this.userId = assessment.getUser().getId();
        this.criteriaId = assessment.getCriteria().getId();
        this.score = assessment.getScore();
        this.comment = assessment.getComment();
    }

    @Override
    public String toString() {
        return "AssessmentDto{" +
                "userId=" + userId +
                ", criteriaId=" + criteriaId +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}


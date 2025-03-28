package com.brainnotfound.employeeassessmentbe.DTO.response;

import com.brainnotfound.employeeassessmentbe.models.Assessment;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AssessmentResponse {
    @Id
    private long id;

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

    public AssessmentResponse(Long ID, Long userId, Long criteriaId, Integer score, String comment) {
        this.id = ID;
        this.userId = userId;
        this.criteriaId = criteriaId;
        this.score = score;
        this.comment = comment;
    }

    public AssessmentResponse(Assessment assessment) {
        this.id = assessment.getId();
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


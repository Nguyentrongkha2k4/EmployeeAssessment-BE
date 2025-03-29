package com.brainnotfound.employeeassessmentbe.DTO.response;

import java.time.LocalDate;

import com.brainnotfound.employeeassessmentbe.models.Assessment;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AssessmentResponse {
    private Long id;

    private Long userId;
    private Long criteriaId;
    private Integer score;
    private String comment;
    private LocalDate updateAt;

    public AssessmentResponse() {
    }
    public AssessmentResponse(Long userId, Long criteriaId, Integer score, String comment, LocalDate updateAt) {
        this.userId = userId;
        this.criteriaId = criteriaId;
        this.score = score;
        this.comment = comment;
        this.updateAt = updateAt;
    }

    public AssessmentResponse(Long ID, Long userId, Long criteriaId, Integer score, String comment, LocalDate updateAt) {
        this.id = ID;
        this.userId = userId;
        this.criteriaId = criteriaId;
        this.score = score;
        this.comment = comment;
        this.updateAt = updateAt;
    }

    public AssessmentResponse(Assessment assessment) {
        this.id = assessment.getId();
        this.userId = assessment.getUser().getId();
        this.criteriaId = assessment.getCriteria().getId();
        this.score = assessment.getScore();
        this.comment = assessment.getComment();
        this.updateAt = assessment.getUpdateAt();
    }

    @Override
    public String toString() {
        return "AssessmentDto{" +
                "userId=" + userId +
                ", criteriaId=" + criteriaId +
                ", score=" + score +
                ", comment='" + comment + ", updateAt=" + updateAt + '\'' +
                '}';
    }
}


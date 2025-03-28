package com.brainnotfound.employeeassessmentbe.DTO.request;


import lombok.Data;

@Data
public class AssessmentRequest {
    private Long criteriaId;
    private Integer score;
    private Long userId;
    private String comment;

    public AssessmentRequest(Long userId, Long criteriaId, Integer score, String comment) {
        this.userId = userId;
        this.criteriaId = criteriaId;
        this.score = score;
        this.comment = comment;
    }
}

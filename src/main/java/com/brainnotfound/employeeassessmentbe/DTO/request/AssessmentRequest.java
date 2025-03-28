package com.brainnotfound.employeeassessmentbe.DTO.request;


import lombok.Data;

@Data
public class AssessmentRequest {
    private Long criteriaId;
    private Integer score;
    private Long userId;
    private String comment;
}

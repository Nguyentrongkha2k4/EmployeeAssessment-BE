package com.brainnotfound.employeeassessmentbe.DTO.request;

import lombok.Data;

@Data
public class AssessmentReq {
    private Long criteriaId;
    private Integer score;
    private String comment;
}

package com.brainnotfound.employeeassessmentbe.DTO.request;

import lombok.Data;

@Data
public class AssessmentMy {
    private Long criteriaId;
    private Integer score;
    private String comment;
}

package com.brainnotfound.employeeassessmentbe.DTO.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AssessmentMy {
    private Long criteriaId;
    private Integer score;
    @JsonIgnore
    private Long userId;
    private String comment;
}

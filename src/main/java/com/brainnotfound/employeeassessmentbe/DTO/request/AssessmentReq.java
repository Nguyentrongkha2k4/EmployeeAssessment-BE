package com.brainnotfound.employeeassessmentbe.DTO.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class AssessmentReq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long criteriaId;
    private Integer score;
    private String comment;
}

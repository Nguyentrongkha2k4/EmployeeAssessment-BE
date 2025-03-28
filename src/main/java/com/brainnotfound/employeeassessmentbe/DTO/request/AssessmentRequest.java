package com.brainnotfound.employeeassessmentbe.DTO.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class AssessmentRequest {
    private Long criteriaId;
    private Integer score;
    private Long userId;
    private String comment;
}

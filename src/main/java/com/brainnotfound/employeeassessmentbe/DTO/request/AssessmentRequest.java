package com.brainnotfound.employeeassessmentbe.DTO.request;


import com.brainnotfound.employeeassessmentbe.exception.AppException;
import com.brainnotfound.employeeassessmentbe.exception.ErrorCode;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentRequest {
    private Long criteriaId;
    @Min(value = 0, message = "ASSESSMENT_SCORE_INVALID")
    private Integer score;
    private Long userId;
    private String comment;

    public AssessmentRequest(Long userId, Long criteriaId, Integer score, String comment) {
        if (score < 0){
            throw new AppException(ErrorCode.ASSESSMENT_SCORE_INVALID);
        }
        this.userId = userId;
        this.criteriaId = criteriaId;
        this.score = score;
        this.comment = comment;
    }
}

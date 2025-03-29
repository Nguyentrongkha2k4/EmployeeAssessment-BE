package com.brainnotfound.employeeassessmentbe.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriteriaReq {
    private String name;
    private String description;

}

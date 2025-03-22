package com.brainnotfound.employeeassessmentbe.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private int status;
    private String message;
    private String token;

    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
} 
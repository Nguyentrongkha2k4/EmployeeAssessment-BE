package com.brainnotfound.employeeassessmentbe.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brainnotfound.employeeassessmentbe.DTO.ResponseObject;
import com.brainnotfound.employeeassessmentbe.DTO.request.AuthenticationRequest;
import com.brainnotfound.employeeassessmentbe.DTO.response.AuthenticationResponse;
import com.brainnotfound.employeeassessmentbe.services.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication API")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Login")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success")
    })
    @PostMapping("/login")
    public ResponseObject<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        ResponseObject<AuthenticationResponse> responseObject = ResponseObject.<AuthenticationResponse>builder()
                                                                            .status(200)
                                                                            .message("LoginSuccess")
                                                                            .data(authenticationService.login(request))
                                                                            .build();
        return responseObject;
    }

}
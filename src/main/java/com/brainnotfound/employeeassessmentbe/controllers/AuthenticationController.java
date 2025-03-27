package com.brainnotfound.employeeassessmentbe.controllers;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brainnotfound.employeeassessmentbe.DTO.ResponseObject;
import com.brainnotfound.employeeassessmentbe.DTO.request.AuthenticationRequest;
import com.brainnotfound.employeeassessmentbe.DTO.request.UserRegisterRequest;
import com.brainnotfound.employeeassessmentbe.DTO.response.AuthenticationResponse;
import com.brainnotfound.employeeassessmentbe.models.LoginRequest;
import com.brainnotfound.employeeassessmentbe.models.User;
import com.brainnotfound.employeeassessmentbe.repositories.UserRepository;
import com.brainnotfound.employeeassessmentbe.services.AuthenticationService;
import com.brainnotfound.employeeassessmentbe.services.UserService;

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
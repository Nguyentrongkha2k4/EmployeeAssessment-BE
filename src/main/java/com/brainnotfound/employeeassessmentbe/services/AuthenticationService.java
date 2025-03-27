package com.brainnotfound.employeeassessmentbe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainnotfound.employeeassessmentbe.DTO.request.AuthenticationRequest;
import com.brainnotfound.employeeassessmentbe.DTO.response.AuthenticationResponse;
import com.brainnotfound.employeeassessmentbe.component.JwtAuthentication;
import com.brainnotfound.employeeassessmentbe.exception.AppException;
import com.brainnotfound.employeeassessmentbe.exception.ErrorCode;
import com.brainnotfound.employeeassessmentbe.models.LoginRequest;
import com.brainnotfound.employeeassessmentbe.models.User;
import com.brainnotfound.employeeassessmentbe.repositories.UserRepository;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtAuthentication jwtAuthentication;

    public AuthenticationResponse login(AuthenticationRequest request){
    
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));

        String jwtToken = jwtAuthentication.authenticated(request.getPassword(), user);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder().token(jwtToken).build();

        return authenticationResponse;
    }
}

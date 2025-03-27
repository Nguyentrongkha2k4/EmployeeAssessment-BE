package com.brainnotfound.employeeassessmentbe.controllers;

import com.brainnotfound.employeeassessmentbe.DTO.ResponseObject;
import com.brainnotfound.employeeassessmentbe.DTO.response.UserResponse;
import com.brainnotfound.employeeassessmentbe.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/supervisee")
@RestController
public class SuperviseeController {
    private final UserService userService;

    public SuperviseeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseObject<List<UserResponse>> getAllSupervisee() {
        Long supervisorId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseObject.<List<UserResponse>>builder()
                .status(200)
                .message("Success")
                .data(null) //TODO: Implement userService.getAllSupervisee(supervisorId)
                .build();
    }

    @GetMapping("/{superviseeId}")
    public ResponseObject<UserResponse> getSupervisee(@PathVariable Long superviseeId) {
        Long supervisorId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseObject.<UserResponse>builder()
                .status(200)
                .message("Success")
                .data(null) //TODO: Implement userService.getSupervisee(supervisorId, superviseeId)
                .build();
    }
}

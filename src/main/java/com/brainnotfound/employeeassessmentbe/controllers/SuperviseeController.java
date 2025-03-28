package com.brainnotfound.employeeassessmentbe.controllers;

import com.brainnotfound.employeeassessmentbe.DTO.AssessmentDto;
import com.brainnotfound.employeeassessmentbe.DTO.ResponseObject;
import com.brainnotfound.employeeassessmentbe.DTO.response.UserResponse;
import com.brainnotfound.employeeassessmentbe.models.User;
import com.brainnotfound.employeeassessmentbe.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/supervisee")
@RestController
public class SuperviseeController {
    private final UserService userService;

    public SuperviseeController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all supervisee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
    })
    @GetMapping("/all")
    public ResponseObject<List<UserResponse>> getAllSupervisee() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserById(Long.parseLong(userId));
        List<UserResponse> supervisees = userService.getAllSupervisee(user);
        //TODO: implement get all supervisee of a supervisor
        return ResponseObject.<List<UserResponse>>builder()
                .status(200)
                .message("Success")
                .data(supervisees)
                .build();
    }

    @Operation(summary = "Get supervisee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping("/{superviseeId}")
    public ResponseObject<UserResponse> getSupervisee(@PathVariable Long superviseeId) {
        //TODO: implement get supervisee by id
        User supervisee = userService.getUserById(superviseeId);
        if (supervisee == null) {
            return ResponseObject.<UserResponse>builder()
                    .status(404)
                    .message("Supervisee not found")
                    .data(null)
                    .build();
        }
        return ResponseObject.<UserResponse>builder()
                .status(200)
                .message("Success")
                .data(new UserResponse(supervisee.getId(), supervisee.getUsername(), supervisee.getRole(), supervisee.getSupervisor().getId()))
                .build();
    }

    

}

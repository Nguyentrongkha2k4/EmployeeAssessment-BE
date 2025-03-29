package com.brainnotfound.employeeassessmentbe.controllers;

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
    public ResponseObject<UserResponse> getSupervisee(@PathVariable("superviseeId") Long superviseeId) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User supervisor = userService.getUserById(Long.parseLong(userId));
        User supervisee = userService.getUserById(superviseeId);
        if (supervisee == null || !supervisee.getSupervisor().getId().equals(supervisor.getId())) {
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

    @Operation(summary = "Assign supervisor to supervisee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PostMapping("/assignSupervisor/{superviseeId}")
    public ResponseObject<UserResponse> assignSupervisor(@PathVariable Long superviseeId) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Long supervisorId = Long.parseLong(userId);

        if (superviseeId.equals(supervisorId)) {
            return ResponseObject.<UserResponse>builder()
                    .status(403)
                    .message("Forbidden: Cannot assign yourself as your own supervisor")
                    .data(null)
                    .build();
        }

        userService.assignSupervisor(superviseeId, supervisorId);
        return ResponseObject.<UserResponse>builder()
                .status(200)
                .message("Supervisor assigned successfully")
                .data(null)
                .build();
    }

}

package com.brainnotfound.employeeassessmentbe.controllers;

import com.brainnotfound.employeeassessmentbe.DTO.ResponseObject;
import com.brainnotfound.employeeassessmentbe.DTO.response.UserResponse;
import com.brainnotfound.employeeassessmentbe.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all supervisee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
    })
    @GetMapping("/all")
    public ResponseObject<List<UserResponse>> getAllSupervisee() {
        //TODO: implement get all supervisee of a supervisor
        return null;
    }

    @Operation(summary = "Get supervisee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping("/{superviseeId}")
    public ResponseObject<UserResponse> getSupervisee(@PathVariable Long superviseeId) {
        //TODO: implement get supervisee by id
        return null;
    }
}

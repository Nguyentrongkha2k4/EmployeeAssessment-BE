package com.brainnotfound.employeeassessmentbe.controllers;

import com.brainnotfound.employeeassessmentbe.models.User;
import com.brainnotfound.employeeassessmentbe.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "User API")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all users")
    })
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}

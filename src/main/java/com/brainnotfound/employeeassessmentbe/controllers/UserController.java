package com.brainnotfound.employeeassessmentbe.controllers;

import com.brainnotfound.employeeassessmentbe.DTO.ResponseObject;
import com.brainnotfound.employeeassessmentbe.DTO.request.UserRegisterRequest;
import com.brainnotfound.employeeassessmentbe.DTO.response.UserResponse;
import com.brainnotfound.employeeassessmentbe.mapper.UserMapper;
import com.brainnotfound.employeeassessmentbe.models.User;
import com.brainnotfound.employeeassessmentbe.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User API")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    @GetMapping("/getall")
    public ResponseObject<List<UserResponse>> getAllUsers() {
        ResponseObject<List<UserResponse>> responseObject = ResponseObject.<List<UserResponse>>builder()
                                                                                    .status(200)
                                                                                    .message("getAllUserSuccess")
                                                                                    .data(userService.getAllUsers().stream().map(
                                                                                        (user)-> userMapper.toUserResponse(user)
                                                                                    ).collect(Collectors.toList()))
                                                                                    .build();
        return responseObject;
    }

    @Operation(summary = "Register user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    @PostMapping("/register")
    public ResponseObject<Null> register(@RequestBody UserRegisterRequest request) {
        userService.userRegister(request);
        ResponseObject<Null> responseObject = ResponseObject.<Null>builder()
                                                                .status(200)
                                                                .message("RegisterSuccess")
                                                                .data(null)
                                                                .build();
        return responseObject;
    }

    @Operation(summary = "Get logged in user info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })

    @GetMapping("/me")
    public ResponseObject<UserResponse> getMe() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserById(Long.parseLong(userId));
        return ResponseObject.<UserResponse>builder()
                .status(200)
                .message("Success")
                .data(userMapper.toUserResponse(user))
                .build();
    }


}

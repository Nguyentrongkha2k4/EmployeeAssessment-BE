package com.brainnotfound.employeeassessmentbe.mapper;

import com.brainnotfound.employeeassessmentbe.DTO.response.UserResponse;
import com.brainnotfound.employeeassessmentbe.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .supervisor(user.getSupervisor() != null ? user.getSupervisor().getId() : null)
                .build();
    }
}

package com.brainnotfound.employeeassessmentbe.DTO;

import com.brainnotfound.employeeassessmentbe.models.User;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}

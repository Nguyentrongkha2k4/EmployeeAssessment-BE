package com.brainnotfound.employeeassessmentbe.services;

import java.util.List;
import java.util.stream.Collectors;

import com.brainnotfound.employeeassessmentbe.DTO.response.UserResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.brainnotfound.employeeassessmentbe.DTO.request.UserRegisterRequest;
import com.brainnotfound.employeeassessmentbe.exception.AppException;
import com.brainnotfound.employeeassessmentbe.exception.ErrorCode;
import com.brainnotfound.employeeassessmentbe.models.User;
import com.brainnotfound.employeeassessmentbe.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User userRegister(UserRegisterRequest request){
            if (userRepository.existsByUsername(request.getUsername())) {
                throw new AppException(ErrorCode.USER_EXISTED);
            }
            User user = User.builder()
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role("USER")
                                .build();
            return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    public List<UserResponse> getAllSupervisee(User supervisor) {
        List<User> supervisees = userRepository.findBySupervisor(supervisor);
        return supervisees.stream()
                .map(user -> new UserResponse(user.getId(), user.getUsername(), user.getRole(), supervisor.getId()))
                .collect(Collectors.toList());
    }
    public void assignSupervisor(Long superviseeId, Long supervisorId) {
        User supervisee = userRepository.findById(superviseeId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        User supervisor = userRepository.findById(supervisorId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        supervisee.setSupervisor(supervisor);
        userRepository.save(supervisee);
    }

}

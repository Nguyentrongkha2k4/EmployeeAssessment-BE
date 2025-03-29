package com.brainnotfound.employeeassessmentbe;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.brainnotfound.employeeassessmentbe.DTO.request.UserRegisterRequest;
import com.brainnotfound.employeeassessmentbe.DTO.response.UserResponse;
import com.brainnotfound.employeeassessmentbe.exception.AppException;
import com.brainnotfound.employeeassessmentbe.exception.ErrorCode;
import com.brainnotfound.employeeassessmentbe.models.User;
import com.brainnotfound.employeeassessmentbe.repositories.UserRepository;
import com.brainnotfound.employeeassessmentbe.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = User.builder()
                .id(1L)
                .username("testUser")
                .password("hashedPassword")
                .role("USER")
                .build();
    }

    @Test
    void testUserRegister_Success() {
        // Given
        UserRegisterRequest request = new UserRegisterRequest("newUser", "password123");

        when(userRepository.existsByUsername("newUser")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // When
        User savedUser = userService.userRegister(request);

        // Then
        assertNotNull(savedUser);
        assertEquals("testUser", savedUser.getUsername());
        assertEquals("USER", savedUser.getRole());
    }

    @Test
    void testUserRegister_UserAlreadyExists() {
        // Given
        UserRegisterRequest request = new UserRegisterRequest("existingUser", "password123");

        when(userRepository.existsByUsername("existingUser")).thenReturn(true);

        // When & Then
        AppException exception = assertThrows(AppException.class, () -> userService.userRegister(request));
        assertEquals(ErrorCode.USER_EXISTED, exception.getErrorCode());
    }

    @Test
    void testGetUserById_Success() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        // When
        User foundUser = userService.getUserById(1L);

        // Then
        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
    }

    @Test
    void testGetUserById_UserNotFound() {
        // Given
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        // When & Then
        AppException exception = assertThrows(AppException.class, () -> userService.getUserById(2L));
        assertEquals(ErrorCode.USER_NOT_EXISTED, exception.getErrorCode());
    }

    @Test
    void testGetAllUsers() {
        // Given
        List<User> users = Arrays.asList(mockUser, User.builder()
        .id((long)3)
        .username("string")
        .password("$2a$10$9FL9nF5d67En3poiTtVJouRDYNVWxYKWXiXhOhyC9cQLaDDyv3goy")
        .role("USER")
        .build());
        when(userRepository.findAll()).thenReturn(users);

        // When
        List<User> result = userService.getAllUsers();

        // Then
        assertEquals(2, result.size());
        assertEquals("testUser", result.get(0).getUsername());
    }

    @Test
    void testGetAllSupervisee() {
        // Given
        User supervisor = User.builder()
        .id((long)3)
        .username("string")
        .password("$2a$10$9FL9nF5d67En3poiTtVJouRDYNVWxYKWXiXhOhyC9cQLaDDyv3goy")
        .role("USER")
        .build();
        List<User> supervisees = Arrays.asList(
                new User(4L, "supervisee1", "pass", "USER", supervisor),
                new User(5L, "supervisee2", "pass", "USER", supervisor)
        );

        when(userRepository.findBySupervisor(supervisor)).thenReturn(supervisees);

        // When
        List<UserResponse> responseList = userService.getAllSupervisee(supervisor);

        // Then
        assertEquals(2, responseList.size());
        assertEquals("supervisee1", responseList.get(0).getUsername());
    }
}

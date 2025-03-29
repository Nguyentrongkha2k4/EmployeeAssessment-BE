package com.brainnotfound.employeeassessmentbe;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.brainnotfound.employeeassessmentbe.DTO.request.AuthenticationRequest;
import com.brainnotfound.employeeassessmentbe.DTO.response.AuthenticationResponse;
import com.brainnotfound.employeeassessmentbe.component.JwtAuthentication;
import com.brainnotfound.employeeassessmentbe.exception.AppException;
import com.brainnotfound.employeeassessmentbe.exception.ErrorCode;
import com.brainnotfound.employeeassessmentbe.models.User;
import com.brainnotfound.employeeassessmentbe.repositories.UserRepository;
import com.brainnotfound.employeeassessmentbe.services.AuthenticationService;



@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtAuthentication jwtAuthentication;

    @InjectMocks
    private AuthenticationService authenticationService;

    private User mockUser;
    
    @BeforeEach
    void setUp() {
        mockUser = new User();
        mockUser.setUsername("string");
        mockUser.setPassword("$2a$10$9FL9nF5d67En3poiTtVJouRDYNVWxYKWXiXhOhyC9cQLaDDyv3goy");
    }
    
    @Test
    void testLogin_Success() {
        // Given
        AuthenticationRequest request = new AuthenticationRequest("string", "string");
        String mockToken = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJkZXYiLCJzdWIiOiIzIiwiZXhwIjoxNzQzMjMyNjI3LCJpYXQiOjE3NDMxNDYyMjcsInNjb3BlIjoiVVNFUiJ9.YxIQIoOLSScb2VX33HwHhJvsaHAhrQQ61K_ZKbUmmTPsKN5mQL8V42ZdlrZtQh3gAn32XYwcSyjnTYqjvcOgkg";

        when(userRepository.findByUsername("string")).thenReturn(Optional.of(mockUser));
        when(jwtAuthentication.authenticated("string", mockUser)).thenReturn(mockToken);

        // When
        AuthenticationResponse response = authenticationService.login(request);

        // Then
        assertNotNull(response);
        assertEquals(mockToken, response.getToken());
    }

    @Test
    void testLogin_UserNotFound() {
        // Given
        AuthenticationRequest request = new AuthenticationRequest("string", "string");

        when(userRepository.findByUsername("string")).thenReturn(Optional.empty());

        // When & Then
        AppException exception = assertThrows(AppException.class, () -> authenticationService.login(request));
        assertEquals(ErrorCode.USER_NOT_EXISTED, exception.getErrorCode());
    }   
}

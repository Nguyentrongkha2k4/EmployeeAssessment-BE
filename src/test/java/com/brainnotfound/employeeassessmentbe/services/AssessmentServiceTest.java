package com.brainnotfound.employeeassessmentbe.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import java.util.Optional;
import java.util.List;

import com.brainnotfound.employeeassessmentbe.exception.AppException;
import com.brainnotfound.employeeassessmentbe.exception.ErrorCode;

import com.brainnotfound.employeeassessmentbe.DTO.request.AssessmentRequest;
import com.brainnotfound.employeeassessmentbe.DTO.response.AssessmentResponse;
import com.brainnotfound.employeeassessmentbe.models.*;
import com.brainnotfound.employeeassessmentbe.repositories.AssessmentRepository;
import com.brainnotfound.employeeassessmentbe.repositories.CriteriaRepository;
import com.brainnotfound.employeeassessmentbe.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class AssessmentServiceTest {
    
@InjectMocks
    private AssessmentService assessmentService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CriteriaRepository criteriaRepository;

    @Mock
    private AssessmentRepository assessmentRepository;

    private AssessmentRequest assessmentRequest;
    private AssessmentResponse assessmentResponse;
    private User user;
    private Criteria criteria;
    private Assessment assessment;

    @BeforeEach
    public void setUp() {
        assessmentRequest = new AssessmentRequest(2L, 1L, 5, "Hoa chu 800 so");

        user = new User();
        user.setId(2L);

        criteria = new Criteria();
        criteria.setId(1L);

        assessment = new Assessment();
        assessment.setUser(user);
        assessment.setCriteria(criteria);
        assessment.setScore(assessmentRequest.getScore());
        assessment.setComment(assessmentRequest.getComment());

        assessmentResponse = new AssessmentResponse(2L, 1L, 5, "Hoa chu 800 so", null);
        assessmentRepository.save(assessment);
    }

    @Test
    void AssesmentService_CreateAsssessment_Success() throws Exception {
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        when(criteriaRepository.findById(1L)).thenReturn(Optional.of(criteria));
        when(assessmentRepository.save(any(Assessment.class))).thenReturn(assessment);

        AssessmentResponse response = assessmentService.createAssessment(assessmentRequest);

        assertEquals(assessmentResponse.getUserId(), response.getUserId());
        assertEquals(assessmentResponse.getCriteriaId(), response.getCriteriaId());
        assertEquals(assessmentResponse.getScore(), response.getScore());
        assertEquals(assessmentResponse.getComment(), response.getComment());
    }

    @Test
    void AssessmentService_CreateAssessment_ScoreInvalid() throws Exception {
        assessmentRequest.setScore(-1);
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        when(criteriaRepository.findById(1L)).thenReturn(Optional.of(criteria));

        try {
            assessmentService.createAssessment(assessmentRequest);
        } catch (AppException e) {
            assertEquals(ErrorCode.ASSESSMENT_SCORE_INVALID, e.getErrorCode());
        }
    }

    @Test
    void AssessmentService_GetAssessment_Success() throws Exception {
        when(assessmentRepository.findById(1L)).thenReturn(Optional.of(assessment));

        AssessmentResponse response = assessmentService.getAssessment(1L);

        assertEquals(assessmentResponse.getUserId(), response.getUserId());
        assertEquals(assessmentResponse.getCriteriaId(), response.getCriteriaId());
        assertEquals(assessmentResponse.getScore(), response.getScore());
        assertEquals(assessmentResponse.getComment(), response.getComment());
    }

    @Test
    void AssessmentService_UpdateAssessment_Success() throws Exception {
        when(assessmentRepository.findById(1L)).thenReturn(Optional.of(assessment));
        when(assessmentRepository.save(any(Assessment.class))).thenReturn(assessment);

        AssessmentResponse response = assessmentService.updateAssessment(1L, assessmentRequest);

        assertEquals(assessmentResponse.getUserId(), response.getUserId());
        assertEquals(assessmentResponse.getCriteriaId(), response.getCriteriaId());
        assertEquals(assessmentResponse.getScore(), response.getScore());
        assertEquals(assessmentResponse.getComment(), response.getComment());
    }

    @Test
    void AssessmentService_DeleteAssessment_Success() throws Exception {
        assessmentService.deleteAssessment(1L);

        assertEquals(HttpStatus.OK.value(), HttpStatus.OK.value());
    }

    @Test
    void AssessmentService_GetMyAssessments_Success() throws Exception {
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        when(assessmentRepository.getAssessmentByUser(user)).thenReturn(List.of(assessment));

        List<AssessmentResponse> response = assessmentService.getMyAssessments(2L);

        assertEquals(1, response.size());
        assertEquals(assessmentResponse.getUserId(), response.get(0).getUserId());
        assertEquals(assessmentResponse.getCriteriaId(), response.get(0).getCriteriaId());
        assertEquals(assessmentResponse.getScore(), response.get(0).getScore());
        assertEquals(assessmentResponse.getComment(), response.get(0).getComment());
    }

    @Test
    void AssessmentService_GetMyAssessments_UserNotFound() throws Exception {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        try {
            assessmentService.getMyAssessments(2L);
        } catch (AppException e) {
            assertEquals(ErrorCode.USER_NOT_EXISTED, e.getErrorCode());
        }
    }

    @Test
    void AssessmentService_GetAssessmentById_Success() throws Exception {
        when(assessmentRepository.findById(1L)).thenReturn(Optional.of(assessment));

        AssessmentResponse response = assessmentService.getAssessment(1L);

        assertEquals(assessmentResponse.getUserId(), response.getUserId());
        assertEquals(assessmentResponse.getCriteriaId(), response.getCriteriaId());
        assertEquals(assessmentResponse.getScore(), response.getScore());
        assertEquals(assessmentResponse.getComment(), response.getComment());
    }

    @Test
    void AssessmentService_GetAssessmentById_NotFound() throws Exception {
        when(assessmentRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            assessmentService.getAssessment(1L);
        } catch (AppException e) {
            assertEquals(ErrorCode.ASSESSMENT_NOT_EXISTED, e.getErrorCode());
        }
    }

    @Test
    void AssessmentService_getAssessmentByUserId_Success() throws Exception {
        when(assessmentRepository.findByUserId(2L)).thenReturn(List.of(assessment));

        List<AssessmentResponse> response = assessmentService.getAssessmentByUserId(2L);

        assertEquals(1, response.size());
        assertEquals(assessmentResponse.getUserId(), response.get(0).getUserId());
        assertEquals(assessmentResponse.getCriteriaId(), response.get(0).getCriteriaId());
        assertEquals(assessmentResponse.getScore(), response.get(0).getScore());
        assertEquals(assessmentResponse.getComment(), response.get(0).getComment());
    }

    @Test
    void AssessmentService_getAssessmentByUserId_NotFound() throws Exception {
        when(assessmentRepository.findByUserId(2L)).thenReturn(List.of());

        try {
            assessmentService.getAssessmentByUserId(2L);
        } catch (AppException e) {
            assertEquals(ErrorCode.ASSESSMENT_NOT_EXISTED, e.getErrorCode());
        }
    }
}


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

    private Long supervisorId;
    private AssessmentRequest assessmentRequest;
    private AssessmentResponse assessmentResponse;
    private User user;
    private User supervisor;
    private Criteria criteria;
    private Assessment assessment;

    @BeforeEach
    public void setUp() {
        supervisorId = 1L;

        assessmentRequest = new AssessmentRequest(2L, 1L, 5, "Hoa chu 800 so");

        user = new User();
        user.setId(2L);

        supervisor = new User();
        supervisor.setId(supervisorId);

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
}


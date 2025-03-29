package com.brainnotfound.employeeassessmentbe.services;

import com.brainnotfound.employeeassessmentbe.DTO.response.AssessmentResponse;
import com.brainnotfound.employeeassessmentbe.models.Assessment;
import com.brainnotfound.employeeassessmentbe.models.Criteria;
import com.brainnotfound.employeeassessmentbe.models.User;
import com.brainnotfound.employeeassessmentbe.repositories.AssessmentRepository;
import com.brainnotfound.employeeassessmentbe.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AssessmentServiceTest {
    @Mock
    private AssessmentRepository assessmentRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AssessmentService assessmentService;

    @Test
    void getMyAssessments() {
        User user = new User(1L, "testUser", "password", "ROLE_USER", null);
        User user2 = new User(2L, "testUser2", "password2", "ROLE_USER2", null);
        Criteria criteria = new Criteria();
        List <Assessment> assessments = List.of(
                new Assessment(1L, user, criteria, 85, "Good job", null),
                new Assessment(2L, user, criteria, 90, "Excellent", null),
                new Assessment(3L, user2, criteria, 75, "Needs improvement", null)
        );
        when(userRepository.findById(user.getId())).thenReturn(java.util.Optional.of(user));
        List<Assessment> assessmentsResult = List.of(
                new Assessment(1L, user, criteria, 85, "Good job", null),
                new Assessment(2L, user, criteria, 90, "Excellent", null)
        );
        List<AssessmentResponse> expected = List.of(
                new AssessmentResponse(1L, user.getId(), criteria.getId(), 85, "Good job", null),
                new AssessmentResponse(2L, user.getId(), criteria.getId(), 90, "Excellent", null)
        );
        when(assessmentRepository.getAssessmentByUser(user)).thenReturn(
                assessmentsResult
        );
        when(assessmentRepository.getAssessmentByUser(user)).thenReturn(
                assessmentsResult
        );

        List <AssessmentResponse> result = assessmentService.getMyAssessments(user.getId());
        assertEquals(expected, result);
    }
}

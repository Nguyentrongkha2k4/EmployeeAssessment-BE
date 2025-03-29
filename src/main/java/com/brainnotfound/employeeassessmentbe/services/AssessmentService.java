package com.brainnotfound.employeeassessmentbe.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.brainnotfound.employeeassessmentbe.DTO.response.AssessmentResponse;
import com.brainnotfound.employeeassessmentbe.DTO.request.AssessmentMy;
import com.brainnotfound.employeeassessmentbe.DTO.request.AssessmentRequest;
import com.brainnotfound.employeeassessmentbe.DTO.response.AssessmentList;
import com.brainnotfound.employeeassessmentbe.exception.AppException;
import com.brainnotfound.employeeassessmentbe.exception.ErrorCode;
import com.brainnotfound.employeeassessmentbe.models.Assessment;
import com.brainnotfound.employeeassessmentbe.models.Criteria;
import com.brainnotfound.employeeassessmentbe.models.User;
import com.brainnotfound.employeeassessmentbe.repositories.AssessmentRepository;
import com.brainnotfound.employeeassessmentbe.repositories.CriteriaRepository;
import com.brainnotfound.employeeassessmentbe.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class AssessmentService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CriteriaRepository criteriaRepository;

    @Autowired
    private AssessmentRepository assessmentRepository;

    public AssessmentResponse createAssessment(AssessmentRequest dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Criteria criteria = criteriaRepository.findById(dto.getCriteriaId())
                .orElseThrow(() -> new AppException(ErrorCode.CRITERIA_NOT_EXISTED));
        if(dto.getScore() < 0){
            throw new AppException(ErrorCode.ASSESSMENT_SCORE_INVALID);
        }
        Assessment assessment = new Assessment();
        assessment.setUser(user);
        assessment.setCriteria(criteria);
        assessment.setScore(dto.getScore());
        assessment.setComment(dto.getComment());
        assessment.setUpdateAt(LocalDate.now());
        assessmentRepository.save(assessment);
        return new AssessmentResponse(assessment);
    }

    public List<AssessmentResponse> getAllAssessments(Long supervisorId) {
        User supervisor = userRepository.findById(supervisorId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        List<User> supervisees = userRepository.findBySupervisor(supervisor);
        List<Assessment> assessments = supervisees.stream()
                .map(user -> assessmentRepository.getAssessmentByUser(user))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        // List<Assessment> assessments = assessmentRepository.findAll();
        return assessments.stream().map(AssessmentResponse::new).collect(Collectors.toList());
    }

    public AssessmentResponse getAssessment(Long id) {
        Assessment assessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ASSESSMENT_NOT_EXISTED));
        return new AssessmentResponse(assessment);
    }

    public AssessmentResponse updateAssessment(Long id, AssessmentRequest dto) {
        Assessment assessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ASSESSMENT_NOT_EXISTED));

        // User user = userRepository.findById(dto.getUserId())
        //         .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // Criteria criteria = criteriaRepository.findById(dto.getCriteriaId())
        //         .orElseThrow(() -> new AppException(ErrorCode.CRITERIA_NOT_EXISTED));

        // assessment.setUser(user);
        // assessment.setCriteria(criteria);
        assessment.setScore(dto.getScore());
        assessment.setComment(dto.getComment());
        assessment.setUpdateAt(LocalDate.now());
        assessmentRepository.save(assessment);
        return new AssessmentResponse(assessment);
    }

    public List<AssessmentResponse> getMyAssessments(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Assessment> assessments = assessmentRepository.getAssessmentByUser(user);
        return assessments.stream().map(AssessmentResponse::new).collect(Collectors.toList());
    }

    public void deleteAssessment(Long id) {
        assessmentRepository.deleteById(id);
    }

    public List<AssessmentResponse> getAssessmentByUserId(Long userId) {
        List<Assessment> assessments = assessmentRepository.findByUserId(userId);
        if (assessments.isEmpty()) {
            throw new AppException(ErrorCode.ASSESSMENT_NOT_EXISTED);
        }
        return assessments.stream().map(AssessmentResponse::new).collect(Collectors.toList());
    }



    //TODO: Remove services below



    public List<String> getMyFeedback(Long userId) {
        List<AssessmentResponse> assessmentResponse = getAssessmentByUserId(userId);
        return assessmentResponse.stream()
                .map(AssessmentResponse::getComment)
                .collect(Collectors.toList());
    }

    public String postMyFeedback(Long userIdLong, AssessmentMy req) {
        AssessmentRequest newAssessmentRequest = new AssessmentRequest(userIdLong, req.getCriteriaId(), req.getScore(), req.getComment());
        createAssessment(newAssessmentRequest);
        return newAssessmentRequest.getComment();
    }

    public String updateMyFeedback(Long assessId, Long userIdLong, AssessmentMy req) {
        List<AssessmentResponse> assessmentResponse = getAssessmentByUserId(userIdLong);

        AssessmentRequest newAssessmentRequest = new AssessmentRequest(userIdLong, req.getCriteriaId(), req.getScore(), req.getComment());
        updateAssessment(assessId, newAssessmentRequest);
        return newAssessmentRequest.getComment();
    }
    public void deleteMyFeedback(Long assessId, Long userIdLong) {
        List<AssessmentResponse> assessmentResponse = getAssessmentByUserId(userIdLong);
        deleteAssessment(assessId);
    }

    public List<AssessmentList> getSuperviseeAssessment(){
        var context = SecurityContextHolder.getContext();
        Long id = Long.parseLong(context.getAuthentication().getName());

        User supvisor = userService.getUserById(id);

        List<User> users = userRepository.findBySupervisor(supvisor);

        // if (users.isEmpty()){
        //     throw new AppException(ErrorCode.INVALID_KEY);
        // }

        List<AssessmentList> response = new ArrayList<AssessmentList>();
        for (User user : users){
            Assessment assessment = assessmentRepository.findByUser(user);
            AssessmentList assessmentList = AssessmentList.builder()
                                                            .id(assessment.getId())
                                                            .user(user)
                                                            .build();
            response.add(assessmentList);
        }
        return response;
    }

    public List<AssessmentResponse> getAssessmentsBySuperviseeId(Long superviseeId) {
        Long supervisorId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        User supervisor = userRepository.findById(supervisorId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        User supervisee = userRepository.findById(superviseeId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (!supervisee.getSupervisor().getId().equals(supervisor.getId())) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        List<Assessment> assessments = assessmentRepository.getAssessmentByUser(supervisee);
        return assessments.stream().map(AssessmentResponse::new).collect(Collectors.toList());
    }
}
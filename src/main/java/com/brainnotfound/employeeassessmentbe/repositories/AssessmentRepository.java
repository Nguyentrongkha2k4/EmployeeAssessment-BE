package com.brainnotfound.employeeassessmentbe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brainnotfound.employeeassessmentbe.models.Assessment;
import com.brainnotfound.employeeassessmentbe.models.User;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    List<Assessment> getAssessmentByUser(User user);
    Assessment findByUser(User user);
}

package com.brainnotfound.employeeassessmentbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brainnotfound.employeeassessmentbe.models.Assessment;
import com.brainnotfound.employeeassessmentbe.models.User;



public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    Assessment findByUser(User user);
}

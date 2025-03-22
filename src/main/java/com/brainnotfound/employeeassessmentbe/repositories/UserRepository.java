package com.brainnotfound.employeeassessmentbe.repositories;

import com.brainnotfound.employeeassessmentbe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

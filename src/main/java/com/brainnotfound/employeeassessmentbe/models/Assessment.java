package com.brainnotfound.employeeassessmentbe.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many assessments belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Many assessments belong to one criteria
    @ManyToOne
    @JoinColumn(name = "criteria_id")
    private Criteria criteria;

    private Integer score;
    private String comment;
}

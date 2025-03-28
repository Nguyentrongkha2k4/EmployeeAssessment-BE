package com.brainnotfound.employeeassessmentbe.models;

import jakarta.persistence.*;
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
    private User user;
    
    // Many assessments belong to one criteria
    @ManyToOne
    private Criteria criteria;

    private Integer score;
    private String comment;
}

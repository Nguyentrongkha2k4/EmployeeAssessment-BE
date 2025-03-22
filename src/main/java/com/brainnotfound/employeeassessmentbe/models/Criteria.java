package com.brainnotfound.employeeassessmentbe.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Criteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    // One criteria can have many assessments
    @OneToMany(mappedBy = "criteria", cascade = CascadeType.ALL)
    private List<Assessment> assessments = new ArrayList<>();

    // Getters and setters...
}

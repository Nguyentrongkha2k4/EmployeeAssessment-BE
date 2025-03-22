package com.brainnotfound.employeeassessmentbe.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "criterias")
public class Criteria {
    @Id
    private String name;
}

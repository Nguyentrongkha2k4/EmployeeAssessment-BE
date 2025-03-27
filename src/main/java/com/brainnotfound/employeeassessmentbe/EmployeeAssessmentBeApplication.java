package com.brainnotfound.employeeassessmentbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class EmployeeAssessmentBeApplication {

    // public static void main(String[] args) {
    //     SpringApplication.run(EmployeeAssessmentBeApplication.class, args);
    // }
    	static {
		Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeAssessmentBeApplication.class, args);
	}
}
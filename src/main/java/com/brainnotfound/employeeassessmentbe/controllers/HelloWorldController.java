package com.brainnotfound.employeeassessmentbe.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String index() {
        return "Hello, World!";
    }
}

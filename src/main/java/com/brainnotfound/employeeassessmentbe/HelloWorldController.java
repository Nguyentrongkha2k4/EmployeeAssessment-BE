package com.brainnotfound.employeeassessmentbe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public Identity index() {
        return new Identity("404BrainNotFound", false, true);
    }
}

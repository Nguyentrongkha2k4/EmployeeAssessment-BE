package com.brainnotfound.employeeassessmentbe.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Employee Assessment API",
                version = "1.0",
                description = "Employee Assessment API haha"
        )
)
public class OpenAPIConfig {
}

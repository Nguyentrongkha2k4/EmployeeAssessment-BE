package com.brainnotfound.employeeassessmentbe.configuration;


import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.brainnotfound.employeeassessmentbe.models.User;
import com.brainnotfound.employeeassessmentbe.repositories.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationConfiguration {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if (!userRepository.existsByUsername("admin")){
                
                User user = User.builder()
                                .username("admin")
                                .password(passwordEncoder.encode("admin"))
                                .role("ADMIN")
                                .build();
                

                userRepository.save(user);

                log.warn("hr account has been crated by default. ");
            }
        };
    }
}

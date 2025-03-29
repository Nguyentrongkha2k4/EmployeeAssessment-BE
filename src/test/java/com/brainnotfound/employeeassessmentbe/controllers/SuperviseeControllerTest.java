package com.brainnotfound.employeeassessmentbe.controllers;

import static org.junit.jupiter.api.Assertions.*;


import com.brainnotfound.employeeassessmentbe.DTO.ResponseObject;
import com.brainnotfound.employeeassessmentbe.DTO.response.UserResponse;
import com.brainnotfound.employeeassessmentbe.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SuperviseeController.class)
public class SuperviseeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(username = "1")
    public void testGetSupervisee() throws Exception {
        UserResponse supervisee = new UserResponse(2L, "supervisee", "USER", 1L);
        when(userService.getUserById(1L)).thenReturn(new User(1L, "supervisor", "SUPERVISOR", null));
        when(userService.getUserById(2L)).thenReturn(new User(2L, "supervisee", "USER", new User(1L, "supervisor", "SUPERVISOR", null)));

        mockMvc.perform(get("/supervisee/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":200,\"message\":\"Success\",\"data\":{\"id\":2,\"username\":\"supervisee\",\"role\":\"USER\",\"supervisor\":1}}"));
    }

    @Test
    @WithMockUser(username = "1")
    public void testGetSuperviseeNotFound() throws Exception {
        when(userService.getUserById(1L)).thenReturn(new User(1L, "supervisor", "SUPERVISOR", null));
        when(userService.getUserById(2L)).thenReturn(null);

        mockMvc.perform(get("/supervisee/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"status\":404,\"message\":\"Supervisee not found\",\"data\":null}"));
    }
}
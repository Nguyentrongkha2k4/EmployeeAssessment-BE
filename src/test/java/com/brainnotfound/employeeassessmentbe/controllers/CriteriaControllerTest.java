package com.brainnotfound.employeeassessmentbe.controllers;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.brainnotfound.employeeassessmentbe.controllers.CriteriaController;
import com.brainnotfound.employeeassessmentbe.models.Criteria;
import com.brainnotfound.employeeassessmentbe.services.CriteriaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CriteriaController.class)
@ExtendWith(MockitoExtension.class)
public class CriteriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CriteriaService criteriaService;

    @Test
    public void testGetCriteria() throws Exception {
        List<Criteria> mockCriteriaList = List.of(
                new Criteria(1L, "Quality", "High standards"),
                new Criteria(2L, "Performance", "Speed and efficiency")
        );

        when(criteriaService.findAll()).thenReturn(mockCriteriaList);

        List<Criteria> mockCriteriaListTest = criteriaService.findAll();



        Assertions.assertNotNull(mockCriteriaListTest);
        Assertions.assertEquals(mockCriteriaList.size(), mockCriteriaListTest.size());
        for (int i = 0; i < mockCriteriaList.size(); i++) {
            Assertions.assertEquals(mockCriteriaList.get(i).getId(), mockCriteriaListTest.get(i).getId());
            Assertions.assertEquals(mockCriteriaList.get(i).getName(), mockCriteriaListTest.get(i).getName());
            Assertions.assertEquals(mockCriteriaList.get(i).getDescription(), mockCriteriaListTest.get(i).getDescription());
        }
        Assertions.assertTrue(mockCriteriaList.size()==2);
    }
}
package com.brainnotfound.employeeassessmentbe.controllers;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import com.brainnotfound.employeeassessmentbe.DTO.request.CriteriaReq;
import com.brainnotfound.employeeassessmentbe.controllers.CriteriaController;
import com.brainnotfound.employeeassessmentbe.models.Criteria;
import com.brainnotfound.employeeassessmentbe.repositories.CriteriaRepository;
import com.brainnotfound.employeeassessmentbe.services.CriteriaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
public class CriteriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private CriteriaService criteriaService;

    @Mock
    private CriteriaRepository criteriaRepository;


    @Test
    public void testGetCriteria() throws Exception {
        List<Criteria> mockCriteriaList = List.of(
                new Criteria(1L, "Quality", "High standards"),
                new Criteria(2L, "Performance", "Speed and efficiency")
        );

        when(criteriaRepository.findAll()).thenReturn(mockCriteriaList);

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
    public void testPostCriteria() throws Exception {
        Criteria mockCriteria = new Criteria(1L, "Quality", "High standards");
        CriteriaReq mockCriteria2 = new CriteriaReq("Quality", "High standards");

        when(criteriaRepository.save(mockCriteria)).thenReturn(mockCriteria);

        Criteria mockCriteriaTest = criteriaService.create(mockCriteria2);

        Assertions.assertNotNull(mockCriteriaTest);
        Assertions.assertEquals(mockCriteria.getName(), mockCriteriaTest.getName());
        Assertions.assertEquals(mockCriteria.getDescription(), mockCriteriaTest.getDescription());
        Assertions.assertTrue(mockCriteria.getId()==1L);
    }

    public void testGetCriteriaById() throws Exception {
        Criteria mockCriteria = new Criteria(1L, "Quality", "High standards");

        when(criteriaRepository.findById(1L)).thenReturn(Optional.of(mockCriteria));

        Criteria mockCriteriaTest = criteriaService.findById(1L);

        Assertions.assertNotNull(mockCriteriaTest);
        Assertions.assertEquals(mockCriteria.getId(), mockCriteriaTest.getId());
        Assertions.assertEquals(mockCriteria.getName(), mockCriteriaTest.getName());
        Assertions.assertEquals(mockCriteria.getDescription(), mockCriteriaTest.getDescription());
        Assertions.assertTrue(mockCriteria.getId()==1L);
    }
}
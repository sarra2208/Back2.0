package com.example.test.controller;

import com.example.test.application.service.ConsultationService;
import com.example.test.application.usecase.Consultation.DeleteConsultationUseCase;
import com.example.test.application.usecase.Consultation.UpdateConsultationUseCase;
import com.example.test.domain.model.Consultation;
import com.example.test.web.controller.ConsultationController;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ConsultationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsultationService consultationService;

    @MockBean
    private DeleteConsultationUseCase deleteConsultationUseCase;

    @MockBean
    private UpdateConsultationUseCase updateConsultationUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    private Consultation testConsultation;

    @BeforeEach
    void setUp() {
        testConsultation = new Consultation();
        testConsultation.setId("1");
        testConsultation.setNote("Test Consultation");
        // set other properties as needed
    }

    @Test
@WithMockUser(roles = "USER")
    void testSaveConsultation() throws Exception {
        when(consultationService.saveConsultation(any(Consultation.class))).thenReturn(testConsultation);

        mockMvc.perform(post("/api/v0/consultation/addConsultation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testConsultation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
                //.andExpect(jsonPath("$.note").value("Test Consultation"));

        verify(consultationService, times(1)).saveConsultation(any(Consultation.class));
    }

    @Test
@WithMockUser(roles = "USER")
    void testGetListConsultations() throws Exception {
        when(consultationService.getListConsultations()).thenReturn(List.of(testConsultation));

        mockMvc.perform(get("/api/v0/consultation/listConsultations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"));
               // .andExpect(jsonPath("$[0].note").value("Test Consultation"));

        verify(consultationService, times(1)).getListConsultations();
    }

    @Test
@WithMockUser(roles = "USER")
    void testGetConsultation() throws Exception {
        when(consultationService.getConsultationById("1")).thenReturn(Optional.of(testConsultation));

        mockMvc.perform(get("/api/v0/consultation/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
               // .andExpect(jsonPath("$.note").value("Test Consultation"));

        verify(consultationService, times(1)).getConsultationById("1");
    }

    @Test
@WithMockUser(roles = "USER")
    void testDeleteConsultation() throws Exception {
        doNothing().when(deleteConsultationUseCase).execute("1");

        mockMvc.perform(delete("/api/v0/consultation/1"))
                .andExpect(status().isNoContent());

        verify(deleteConsultationUseCase, times(1)).execute("1");
    }

    @Test
@WithMockUser(roles = "USER")
    void testUpdateConsultationSuccess() throws Exception {
        when(updateConsultationUseCase.execute(eq("1"), any(Consultation.class))).thenReturn(testConsultation);

        mockMvc.perform(put("/api/v0/consultation/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testConsultation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
                //.andExpect(jsonPath("$.note").value("Test Consultation"));

        verify(updateConsultationUseCase, times(1)).execute(eq("1"), any(Consultation.class));
    }

    @Test
@WithMockUser(roles = "USER")
    void testUpdateConsultationFailure() throws Exception {
        when(updateConsultationUseCase.execute(eq("1"), any(Consultation.class)))
                .thenThrow(new RuntimeException("Consultation not found"));

        mockMvc.perform(put("/api/v0/consultation/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testConsultation)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Consultation not found"));

        verify(updateConsultationUseCase, times(1)).execute(eq("1"), any(Consultation.class));
    }
}

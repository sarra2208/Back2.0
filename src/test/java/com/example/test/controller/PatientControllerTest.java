package com.example.test.controller;

import com.example.test.application.service.PatientService;
import com.example.test.application.usecase.patient.DeletePatientUseCase;
import com.example.test.application.usecase.patient.UpdatePatientUseCase;
import com.example.test.domain.model.Patient;
import com.example.test.web.controller.PatientController;
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
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private DeletePatientUseCase deletePatientUseCase;

    @MockBean
    private UpdatePatientUseCase updatePatientUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    private Patient testPatient;

    @BeforeEach
    void setUp() {
        testPatient = new Patient();
        testPatient.setId(1L);
        testPatient.setFirstname("John Doe");
        // set other properties as needed
    }

    @Test
    @WithMockUser(roles = "USER")
    void testSavePatient() throws Exception {
        when(patientService.savePatient(any(Patient.class))).thenReturn(testPatient);

        mockMvc.perform(post("/api/v0/patient/addPatient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testPatient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.firstname").value("John Doe"));

        verify(patientService, times(1)).savePatient(any(Patient.class));
    }

    @Test
@WithMockUser(roles = "USER")
    void testGetListPatients() throws Exception {
        when(patientService.getListPatients()).thenReturn(List.of(testPatient));

        mockMvc.perform(get("/api/v0/patient/listPatients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].firstname").value("John Doe"));

        verify(patientService, times(1)).getListPatients();
    }

    @Test
@WithMockUser(roles = "USER")
    void testGetPatient() throws Exception {
        when(patientService.getPatientById("1")).thenReturn(Optional.of(testPatient));

        mockMvc.perform(get("/api/v0/patient/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.firstname").value("John Doe"));

        verify(patientService, times(1)).getPatientById("1");
    }

    @Test
@WithMockUser(roles = "USER")
    void testDeletePatient() throws Exception {
        doNothing().when(deletePatientUseCase).execute("1");

        mockMvc.perform(delete("/api/v0/patient/1"))
                .andExpect(status().isNoContent());

        verify(deletePatientUseCase, times(1)).execute("1");
    }

    @Test
@WithMockUser(roles = "USER")
    void testUpdatePatientSuccess() throws Exception {
        when(updatePatientUseCase.execute(eq("1"), any(Patient.class))).thenReturn(testPatient);

        mockMvc.perform(put("/api/v0/patient/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testPatient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.firstname").value("John Doe"));

        verify(updatePatientUseCase, times(1)).execute(eq("1"), any(Patient.class));
    }

    @Test
@WithMockUser(roles = "USER")
    void testUpdatePatientFailure() throws Exception {
        when(updatePatientUseCase.execute(eq("1"), any(Patient.class)))
                .thenThrow(new RuntimeException("Patient not found"));

        mockMvc.perform(put("/api/v0/patient/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testPatient)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Patient not found"));

        verify(updatePatientUseCase, times(1)).execute(eq("1"), any(Patient.class));
    }
}

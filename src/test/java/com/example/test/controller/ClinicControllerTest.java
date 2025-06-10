package com.example.test.controller;

import com.example.test.application.service.ClinicService;
import com.example.test.application.usecase.Clinic.DeleteClinicUseCase;
import com.example.test.application.usecase.Clinic.UpdateClinicUseCase;
import com.example.test.domain.model.Clinic;

import com.example.test.web.controller.ClinicController;
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
class ClinicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClinicService clinicService;

    @MockBean
    private DeleteClinicUseCase deleteClinicUseCase;

    @MockBean
    private UpdateClinicUseCase updateClinicUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    private Clinic testClinic;

    @BeforeEach
    void setUp() {
        testClinic = new Clinic();
        testClinic.setId(1L);
        testClinic.setName("Test Clinic");
        // set other properties as needed
    }

    @Test
@WithMockUser(roles = "USER")
    void testSaveClinic() throws Exception {
        when(clinicService.saveClinic(any(Clinic.class))).thenReturn(testClinic);

        mockMvc.perform(post("/api/v0/clinic/addClinic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testClinic)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Test Clinic"));

        verify(clinicService, times(1)).saveClinic(any(Clinic.class));
    }

    @Test
@WithMockUser(roles = "USER")
    void testGetListClinic() throws Exception {
        when(clinicService.getListClinics()).thenReturn(List.of(testClinic));

        mockMvc.perform(get("/api/v0/clinic/listClinics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Test Clinic"));

        verify(clinicService, times(1)).getListClinics();
    }

    @Test
@WithMockUser(roles = "USER")
    void testGetClinic() throws Exception {
        when(clinicService.getClinicById("1")).thenReturn(Optional.of(testClinic));

        mockMvc.perform(get("/api/v0/clinic/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Test Clinic"));

        verify(clinicService, times(1)).getClinicById("1");
    }

    @Test
@WithMockUser(roles = "USER")
    void testDeleteClinic() throws Exception {
        doNothing().when(deleteClinicUseCase).execute("1");

        mockMvc.perform(delete("/api/v0/clinic/1"))
                .andExpect(status().isNoContent());

        verify(deleteClinicUseCase, times(1)).execute("1");
    }

    @Test
@WithMockUser(roles = "USER")
    void testUpdateClinicSuccess() throws Exception {
        when(updateClinicUseCase.execute(eq("1"), any(Clinic.class))).thenReturn(testClinic);

        mockMvc.perform(put("/api/v0/clinic/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testClinic)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Test Clinic"));

        verify(updateClinicUseCase, times(1)).execute(eq("1"), any(Clinic.class));
    }

    @Test
@WithMockUser(roles = "USER")
    void testUpdateClinicFailure() throws Exception {
        when(updateClinicUseCase.execute(eq("1"), any(Clinic.class)))
                .thenThrow(new RuntimeException("Clinic not found"));

        mockMvc.perform(put("/api/v0/clinic/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testClinic)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Clinic not found"));

        verify(updateClinicUseCase, times(1)).execute(eq("1"), any(Clinic.class));
    }
}

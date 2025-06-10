package com.example.test.controller;

import com.example.test.application.service.AppointmentService;
import com.example.test.application.usecase.Appointment.DeleteAppointmentUseCase;
import com.example.test.application.usecase.Appointment.UpdateAppointmentUseCase;
import com.example.test.domain.model.Appointment;
import com.example.test.web.controller.AppointmentController;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private DeleteAppointmentUseCase deleteAppointmentUseCase;

    @MockBean
    private UpdateAppointmentUseCase updateAppointmentUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    private Appointment sampleAppointment;

    @BeforeEach
    void setUp() {
        sampleAppointment = new Appointment();
        // set fields in sampleAppointment as needed
    }

    @Test
@WithMockUser(roles = "USER")
    void testSaveAppointment() throws Exception {
        when(appointmentService.saveAppointment(any(Appointment.class))).thenReturn(sampleAppointment);

        mockMvc.perform(post("/api/v0/appointment/addAppointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleAppointment)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(sampleAppointment)));

        verify(appointmentService, times(1)).saveAppointment(any(Appointment.class));
    }

    @Test
@WithMockUser(roles = "USER")
    void testGetListAppointments() throws Exception {
        List<Appointment> appointments = Arrays.asList(sampleAppointment);
        when(appointmentService.getListAppointments()).thenReturn(appointments);

        mockMvc.perform(get("/api/v0/appointment/listAppointments"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(appointments)));

        verify(appointmentService, times(1)).getListAppointments();
    }

    @Test
@WithMockUser(roles = "USER")
    void testGetAppointment() throws Exception {
        Long id = 1L;
        when(appointmentService.getAppointmentById(id)).thenReturn(Optional.of(sampleAppointment));

        mockMvc.perform(get("/api/v0/appointment/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Optional.of(sampleAppointment))));

        verify(appointmentService, times(1)).getAppointmentById(id);
    }

    @Test
@WithMockUser(roles = "USER")
    void testDeleteAppointment() throws Exception {
        Long id = 1L;
        doNothing().when(deleteAppointmentUseCase).execute(id);

        mockMvc.perform(delete("/api/v0/appointment/{id}", id))
                .andExpect(status().isNoContent());

        verify(deleteAppointmentUseCase, times(1)).execute(id);
    }

    @Test
@WithMockUser(roles = "USER")
    void testUpdateAppointment_Success() throws Exception {
        Long id = 1L;
        when(updateAppointmentUseCase.execute(eq(id), any(Appointment.class))).thenReturn(sampleAppointment);

        mockMvc.perform(put("/api/v0/appointment/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleAppointment)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(sampleAppointment)));

        verify(updateAppointmentUseCase, times(1)).execute(eq(id), any(Appointment.class));
    }

    @Test
@WithMockUser(roles = "USER")
    void testUpdateAppointment_Failure() throws Exception {
        Long id = 1L;
        String errorMessage = "Appointment not found";
        when(updateAppointmentUseCase.execute(eq(id), any(Appointment.class)))
                .thenThrow(new RuntimeException(errorMessage));

        mockMvc.perform(put("/api/v0/appointment/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleAppointment)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(errorMessage));

        verify(updateAppointmentUseCase, times(1)).execute(eq(id), any(Appointment.class));
    }
}

package com.example.test.services;

import com.example.test.application.service.AppointmentService;
import com.example.test.application.usecase.Appointment.GetAppointmentUseCase;
import com.example.test.application.usecase.Appointment.GetListAppointmentUseCase;
import com.example.test.application.usecase.Appointment.SaveAppointmentUseCase;
import com.example.test.domain.model.Appointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    private SaveAppointmentUseCase saveAppointmentUseCase;
    private GetListAppointmentUseCase getListAppointmentUseCase;
    private GetAppointmentUseCase getAppointmentUseCase;
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        saveAppointmentUseCase = mock(SaveAppointmentUseCase.class);
        getListAppointmentUseCase = mock(GetListAppointmentUseCase.class);
        getAppointmentUseCase = mock(GetAppointmentUseCase.class);

        appointmentService = new AppointmentService(
                saveAppointmentUseCase,
                getListAppointmentUseCase,
                getAppointmentUseCase
        );
    }

    @Test
    void testSaveAppointment() {
        Appointment appointment = new Appointment(); // You may want to set fields
        when(saveAppointmentUseCase.execute(appointment)).thenReturn(appointment);

        Appointment result = appointmentService.saveAppointment(appointment);

        assertNotNull(result);
        assertEquals(appointment, result);
        verify(saveAppointmentUseCase, times(1)).execute(appointment);
    }

    @Test
    void testGetListAppointments() {
        Appointment appointment1 = new Appointment();
        Appointment appointment2 = new Appointment();
        List<Appointment> appointments = Arrays.asList(appointment1, appointment2);

        when(getListAppointmentUseCase.execute()).thenReturn(appointments);

        List<Appointment> result = appointmentService.getListAppointments();

        assertEquals(2, result.size());
        assertEquals(appointments, result);
        verify(getListAppointmentUseCase, times(1)).execute();
    }

    @Test
    void testGetAppointmentById() {
        Long id = 1L;
        Appointment appointment = new Appointment();
        when(getAppointmentUseCase.execute(id)).thenReturn(Optional.of(appointment));

        Optional<Appointment> result = appointmentService.getAppointmentById(id);

        assertTrue(result.isPresent());
        assertEquals(appointment, result.get());
        verify(getAppointmentUseCase, times(1)).execute(id);
    }

    @Test
    void testGetAppointmentById_NotFound() {
        Long id = 999L;
        when(getAppointmentUseCase.execute(id)).thenReturn(Optional.empty());

        Optional<Appointment> result = appointmentService.getAppointmentById(id);

        assertFalse(result.isPresent());
        verify(getAppointmentUseCase, times(1)).execute(id);
    }
}


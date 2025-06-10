package com.example.test.services;

import com.example.test.application.service.ClinicService;
import com.example.test.application.usecase.Clinic.GetClinicUseCase;
import com.example.test.application.usecase.Clinic.GetListClinicUseCase;
import com.example.test.application.usecase.Clinic.SaveClinicUseCase;
import com.example.test.domain.model.Clinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClinicServiceTest {

    private SaveClinicUseCase saveClinicUseCase;
    private GetListClinicUseCase getListClinicUseCase;
    private GetClinicUseCase getClinicUseCase;
    private ClinicService clinicService;

    @BeforeEach
    void setUp() {
        saveClinicUseCase = mock(SaveClinicUseCase.class);
        getListClinicUseCase = mock(GetListClinicUseCase.class);
        getClinicUseCase = mock(GetClinicUseCase.class);

        clinicService = new ClinicService(
                saveClinicUseCase,
                getListClinicUseCase,
                getClinicUseCase
        );
    }

    @Test
    void testSaveClinic() {
        Clinic clinic = new Clinic(); // You may want to set fields
        when(saveClinicUseCase.execute(clinic)).thenReturn(clinic);

        Clinic result = clinicService.saveClinic(clinic);

        assertNotNull(result);
        assertEquals(clinic, result);
        verify(saveClinicUseCase, times(1)).execute(clinic);
    }

    @Test
    void testGetListClinics() {
        Clinic clinic1 = new Clinic();
        Clinic clinic2 = new Clinic();
        List<Clinic> clinics = Arrays.asList(clinic1, clinic2);

        when(getListClinicUseCase.execute()).thenReturn(clinics);

        List<Clinic> result = clinicService.getListClinics();

        assertEquals(2, result.size());
        assertEquals(clinics, result);
        verify(getListClinicUseCase, times(1)).execute();
    }

    @Test
    void testGetClinicById() {
        String id = "clinic-123";
        Clinic clinic = new Clinic();
        when(getClinicUseCase.execute(id)).thenReturn(Optional.of(clinic));

        Optional<Clinic> result = clinicService.getClinicById(id);

        assertTrue(result.isPresent());
        assertEquals(clinic, result.get());
        verify(getClinicUseCase, times(1)).execute(id);
    }

    @Test
    void testGetClinicById_NotFound() {
        String id = "non-existent-id";
        when(getClinicUseCase.execute(id)).thenReturn(Optional.empty());

        Optional<Clinic> result = clinicService.getClinicById(id);

        assertFalse(result.isPresent());
        verify(getClinicUseCase, times(1)).execute(id);
    }
}

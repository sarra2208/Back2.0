package com.example.test.services;

import com.example.test.application.service.PatientService;
import com.example.test.application.usecase.patient.GetListPatientsUseCase;
import com.example.test.application.usecase.patient.GetPatientUseCase;
import com.example.test.application.usecase.patient.SavePatientUseCase;
import com.example.test.domain.model.Patient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    private SavePatientUseCase savePatientUseCase;
    private GetListPatientsUseCase getListPatientsUseCase;
    private GetPatientUseCase getPatientUseCase;
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        savePatientUseCase = mock(SavePatientUseCase.class);
        getListPatientsUseCase = mock(GetListPatientsUseCase.class);
        getPatientUseCase = mock(GetPatientUseCase.class);

        patientService = new PatientService(
                savePatientUseCase,
                getListPatientsUseCase,
                getPatientUseCase
        );
    }

    @Test
    void testSavePatient() {
        Patient patient = new Patient(); // Initialize with data if needed
        when(savePatientUseCase.execute(patient)).thenReturn(patient);

        Patient result = patientService.savePatient(patient);

        assertNotNull(result);
        assertEquals(patient, result);
        verify(savePatientUseCase, times(1)).execute(patient);
    }

    @Test
    void testGetListPatients() {
        Patient p1 = new Patient();
        Patient p2 = new Patient();
        List<Patient> expectedList = Arrays.asList(p1, p2);

        when(getListPatientsUseCase.execute()).thenReturn(expectedList);

        List<Patient> result = patientService.getListPatients();

        assertEquals(2, result.size());
        assertEquals(expectedList, result);
        verify(getListPatientsUseCase, times(1)).execute();
    }

    @Test
    void testGetPatientById() {
        String id = "patient-123";
        Patient patient = new Patient();
        when(getPatientUseCase.execute(id)).thenReturn(Optional.of(patient));

        Optional<Patient> result = patientService.getPatientById(id);

        assertTrue(result.isPresent());
        assertEquals(patient, result.get());
        verify(getPatientUseCase, times(1)).execute(id);
    }

    @Test
    void testGetPatientById_NotFound() {
        String id = "missing-id";
        when(getPatientUseCase.execute(id)).thenReturn(Optional.empty());

        Optional<Patient> result = patientService.getPatientById(id);

        assertFalse(result.isPresent());
        verify(getPatientUseCase, times(1)).execute(id);
    }
}


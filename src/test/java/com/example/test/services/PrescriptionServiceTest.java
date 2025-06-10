package com.example.test.services;

import com.example.test.application.service.PrescriptionService;
import com.example.test.application.usecase.Prescription.GetListPrescriptionUseCase;
import com.example.test.application.usecase.Prescription.GetPrescriptionUseCase;
import com.example.test.application.usecase.Prescription.SavePrescriptionUseCase;
import com.example.test.domain.model.Prescription;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrescriptionServiceTest {

    private SavePrescriptionUseCase savePrescriptionUseCase;
    private GetPrescriptionUseCase getPrescriptionUseCase;
    private GetListPrescriptionUseCase getListPrescriptionUseCase;
    private PrescriptionService prescriptionService;

    @BeforeEach
    void setUp() {
        savePrescriptionUseCase = mock(SavePrescriptionUseCase.class);
        getPrescriptionUseCase = mock(GetPrescriptionUseCase.class);
        getListPrescriptionUseCase = mock(GetListPrescriptionUseCase.class);

        prescriptionService = new PrescriptionService(
                savePrescriptionUseCase,
                getPrescriptionUseCase,
                getListPrescriptionUseCase
        );
    }

    @Test
    void testSavePrescription() {
        Prescription prescription = new Prescription(); // Initialize fields as needed
        when(savePrescriptionUseCase.execute(prescription)).thenReturn(prescription);

        Prescription result = prescriptionService.savePrescription(prescription);

        assertNotNull(result);
        assertEquals(prescription, result);
        verify(savePrescriptionUseCase, times(1)).execute(prescription);
    }

    @Test
    void testGetListPrescriptions() {
        Prescription p1 = new Prescription();
        Prescription p2 = new Prescription();
        List<Prescription> expectedList = Arrays.asList(p1, p2);

        when(getListPrescriptionUseCase.execute()).thenReturn(expectedList);

        List<Prescription> result = prescriptionService.getListPrescriptions();

        assertEquals(2, result.size());
        assertEquals(expectedList, result);
        verify(getListPrescriptionUseCase, times(1)).execute();
    }

    @Test
    void testGetPrescriptionById() {
        String id = "prescription-001";
        Prescription prescription = new Prescription();
        when(getPrescriptionUseCase.execute(id)).thenReturn(Optional.of(prescription));

        Optional<Prescription> result = prescriptionService.getPrescriptionById(id);

        assertTrue(result.isPresent());
        assertEquals(prescription, result.get());
        verify(getPrescriptionUseCase, times(1)).execute(id);
    }

    @Test
    void testGetPrescriptionById_NotFound() {
        String id = "non-existent-id";
        when(getPrescriptionUseCase.execute(id)).thenReturn(Optional.empty());

        Optional<Prescription> result = prescriptionService.getPrescriptionById(id);

        assertFalse(result.isPresent());
        verify(getPrescriptionUseCase, times(1)).execute(id);
    }
}

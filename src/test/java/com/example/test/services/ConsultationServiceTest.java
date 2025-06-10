package com.example.test.services;

import com.example.test.application.service.ConsultationService;
import com.example.test.application.usecase.Consultation.GetConsultationUseCase;
import com.example.test.application.usecase.Consultation.GetListConsultationUseCase;
import com.example.test.application.usecase.Consultation.SaveConsultationUseCase;
import com.example.test.domain.model.Consultation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsultationServiceTest {

    private SaveConsultationUseCase saveConsultationUseCase;
    private GetListConsultationUseCase getListConsultationUseCase;
    private GetConsultationUseCase getConsultationUseCase;
    private ConsultationService consultationService;

    @BeforeEach
    void setUp() {
        saveConsultationUseCase = mock(SaveConsultationUseCase.class);
        getListConsultationUseCase = mock(GetListConsultationUseCase.class);
        getConsultationUseCase = mock(GetConsultationUseCase.class);

        consultationService = new ConsultationService(
                saveConsultationUseCase,
                getListConsultationUseCase,
                getConsultationUseCase
        );
    }

    @Test
    void testSaveConsultation() {
        Consultation consultation = new Consultation(); // Set fields if needed
        when(saveConsultationUseCase.execute(consultation)).thenReturn(consultation);

        Consultation result = consultationService.saveConsultation(consultation);

        assertNotNull(result);
        assertEquals(consultation, result);
        verify(saveConsultationUseCase, times(1)).execute(consultation);
    }

    @Test
    void testGetListConsultations() {
        Consultation consultation1 = new Consultation();
        Consultation consultation2 = new Consultation();
        List<Consultation> consultations = Arrays.asList(consultation1, consultation2);

        when(getListConsultationUseCase.execute()).thenReturn(consultations);

        List<Consultation> result = consultationService.getListConsultations();

        assertEquals(2, result.size());
        assertEquals(consultations, result);
        verify(getListConsultationUseCase, times(1)).execute();
    }

    @Test
    void testGetConsultationById() {
        String id = "consult-001";
        Consultation consultation = new Consultation();
        when(getConsultationUseCase.execute(id)).thenReturn(Optional.of(consultation));

        Optional<Consultation> result = consultationService.getConsultationById(id);

        assertTrue(result.isPresent());
        assertEquals(consultation, result.get());
        verify(getConsultationUseCase, times(1)).execute(id);
    }

    @Test
    void testGetConsultationById_NotFound() {
        String id = "non-existent-id";
        when(getConsultationUseCase.execute(id)).thenReturn(Optional.empty());

        Optional<Consultation> result = consultationService.getConsultationById(id);

        assertFalse(result.isPresent());
        verify(getConsultationUseCase, times(1)).execute(id);
    }
}


package com.example.test.services;

import com.example.test.application.service.ElementPrescritService;
import com.example.test.application.usecase.ElementPrescrit.GetElementPrescritUseCase;
import com.example.test.application.usecase.ElementPrescrit.GetListElementPrescritUseCase;
import com.example.test.application.usecase.ElementPrescrit.SaveElementPrescritUseCase;
import com.example.test.domain.model.ElementPrescrit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ElementPrescritServiceTest {

    private SaveElementPrescritUseCase saveElementPrescritUseCase;
    private GetElementPrescritUseCase getElementPrescritUseCase;
    private GetListElementPrescritUseCase getListElementPrescritUseCase;
    private ElementPrescritService elementPrescritService;

    @BeforeEach
    void setUp() {
        saveElementPrescritUseCase = mock(SaveElementPrescritUseCase.class);
        getElementPrescritUseCase = mock(GetElementPrescritUseCase.class);
        getListElementPrescritUseCase = mock(GetListElementPrescritUseCase.class);

        elementPrescritService = new ElementPrescritService(
                saveElementPrescritUseCase,
                getElementPrescritUseCase,
                getListElementPrescritUseCase
        );
    }

    @Test
    void testSaveElementPrescrit() {
        ElementPrescrit element = new ElementPrescrit(); // Set fields if necessary
        when(saveElementPrescritUseCase.execute(element)).thenReturn(element);

        ElementPrescrit result = elementPrescritService.saveElementPrescrit(element);

        assertNotNull(result);
        assertEquals(element, result);
        verify(saveElementPrescritUseCase, times(1)).execute(element);
    }

    @Test
    void testGetListElementPrescrits() {
        ElementPrescrit e1 = new ElementPrescrit();
        ElementPrescrit e2 = new ElementPrescrit();
        List<ElementPrescrit> expectedList = Arrays.asList(e1, e2);

        when(getListElementPrescritUseCase.execute()).thenReturn(expectedList);

        List<ElementPrescrit> result = elementPrescritService.getListElementPrescrits();

        assertEquals(2, result.size());
        assertEquals(expectedList, result);
        verify(getListElementPrescritUseCase, times(1)).execute();
    }

    @Test
    void testGetElementPrescritById() {
        String id = "prescrit-001";
        ElementPrescrit element = new ElementPrescrit();
        when(getElementPrescritUseCase.execute(id)).thenReturn(Optional.of(element));

        Optional<ElementPrescrit> result = elementPrescritService.getElementPrescritById(id);

        assertTrue(result.isPresent());
        assertEquals(element, result.get());
        verify(getElementPrescritUseCase, times(1)).execute(id);
    }

    @Test
    void testGetElementPrescritById_NotFound() {
        String id = "non-existent-id";
        when(getElementPrescritUseCase.execute(id)).thenReturn(Optional.empty());

        Optional<ElementPrescrit> result = elementPrescritService.getElementPrescritById(id);

        assertFalse(result.isPresent());
        verify(getElementPrescritUseCase, times(1)).execute(id);
    }
}

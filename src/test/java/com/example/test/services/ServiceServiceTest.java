package com.example.test.services;

import com.example.test.application.service.ServiceService;
import com.example.test.application.usecase.Service.GetListServiceUseCase;
import com.example.test.application.usecase.Service.GetServiceUseCase;
import com.example.test.application.usecase.Service.SaveServiceUseCase;
import com.example.test.domain.model.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceServiceTest {

    private GetListServiceUseCase getListServiceUseCase;
    private GetServiceUseCase getServiceUseCase;
    private SaveServiceUseCase saveServiceUseCase;
    private ServiceService serviceService;

    @BeforeEach
    void setUp() {
        getListServiceUseCase = mock(GetListServiceUseCase.class);
        getServiceUseCase = mock(GetServiceUseCase.class);
        saveServiceUseCase = mock(SaveServiceUseCase.class);

        serviceService = new ServiceService(getListServiceUseCase, getServiceUseCase, saveServiceUseCase);
    }

    @Test
    void testSaveService_returnsNull() {
        Service service = new Service();
        // Since saveService returns null, we expect null here
        Service result = serviceService.saveService(service);

        assertNull(result);
        // No interaction expected with saveServiceUseCase because saveService returns null directly
        verifyNoInteractions(saveServiceUseCase);
    }

    @Test
    void testGetListServices() {
        Service s1 = new Service();
        Service s2 = new Service();
        List<Service> expectedList = Arrays.asList(s1, s2);

        when(getListServiceUseCase.execute()).thenReturn(expectedList);

        List<Service> result = serviceService.getListServices();

        assertEquals(expectedList, result);
        verify(getListServiceUseCase, times(1)).execute();
    }

    @Test
    void testGetServiceById() {
        String id = "service-123";
        Service service = new Service();

        when(getServiceUseCase.execute(id)).thenReturn(Optional.of(service));

        Optional<Service> result = serviceService.getServiceById(id);

        assertTrue(result.isPresent());
        assertEquals(service, result.get());
        verify(getServiceUseCase, times(1)).execute(id);
    }

    @Test
    void testGetServiceById_NotFound() {
        String id = "notfound";

        when(getServiceUseCase.execute(id)).thenReturn(Optional.empty());

        Optional<Service> result = serviceService.getServiceById(id);

        assertFalse(result.isPresent());
        verify(getServiceUseCase, times(1)).execute(id);
    }
}

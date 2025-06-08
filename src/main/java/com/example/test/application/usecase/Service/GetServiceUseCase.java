package com.example.test.application.usecase.Service;

import com.example.test.domain.model.Service;
import com.example.test.domain.repository.ServiceRepository;

import java.util.Optional;

public class GetServiceUseCase {
    private final ServiceRepository serviceRepository;

    public GetServiceUseCase(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Optional<Service> execute(String id){
        return serviceRepository.findServiceById(id);
    }
}


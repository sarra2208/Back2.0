package com.example.test.application.usecase.Service;

import com.example.test.domain.model.Service;
import com.example.test.domain.repository.ServiceRepository;

import java.util.List;

public class GetListServiceUseCase {

    private final ServiceRepository serviceRepository;

    public GetListServiceUseCase(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Service> execute() {
        return serviceRepository.findAllServices();
    }
}



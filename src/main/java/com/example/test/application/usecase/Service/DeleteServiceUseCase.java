package com.example.test.application.usecase.Service;

import com.example.test.domain.repository.ServiceRepository;

public class DeleteServiceUseCase {
    private final ServiceRepository serviceRepository;





    public DeleteServiceUseCase(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public  void execute(String id) {
        serviceRepository.deleteById(id);
    }
}


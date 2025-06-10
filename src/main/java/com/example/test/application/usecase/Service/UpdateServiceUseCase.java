package com.example.test.application.usecase.Service;

import com.example.test.domain.model.Service;
import com.example.test.domain.repository.ServiceRepository;

public class UpdateServiceUseCase {
    private final ServiceRepository serviceRepository;

    public UpdateServiceUseCase(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
    public Service execute(String id, Service updatedService) {
        return (Service) serviceRepository.findServiceById(id)
                .map(existingService -> {
                    // Mettre à jour uniquement si l'ID existe
                   // existingService.setClinicId(updatedService.getClinicId());
                    existingService.setName(updatedService.getName());
                    existingService.setDescription(updatedService.getDescription());


                    return serviceRepository.save(existingService);
                })
                .orElseThrow(() -> new RuntimeException("⚠️ Appointment not found with ID: " + id));
    }
}

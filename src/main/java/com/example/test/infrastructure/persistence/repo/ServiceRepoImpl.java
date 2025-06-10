package com.example.test.infrastructure.persistence.repo;

import com.example.test.domain.model.Service;

import com.example.test.domain.repository.ServiceRepository;
import com.example.test.infrastructure.persistence.entity.ServiceEntity;
import com.example.test.infrastructure.persistence.mapper.ServiceMapper;
import com.example.test.infrastructure.persistence.repo.jpa.ServiceRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ServiceRepoImpl implements ServiceRepository {
    private final ServiceRepo serviceRepo;

    public ServiceRepoImpl(@Lazy ServiceRepo serviceRepo) {
        this.serviceRepo = serviceRepo;
    }
   @Override
    public List<Service> findAllServices() {
        return serviceRepo.findAll()
                .stream()
                .map(ServiceMapper::toDomain)
                .toList();
    }

    @Override
    public Service save(Service Service) {
        return null;
    }

     @Override
    public Optional<Service> findServiceById(String id) {
        return serviceRepo.findById(id)
                .map(ServiceMapper::toDomain);
    }

    @Override
    public void deleteById(String id) {
        serviceRepo.deleteById(id);
    }
    @Override
    public ServiceEntity update(String id, Service updatedService) {
        return serviceRepo.findById(id)
                .map(existingService -> {
                   // existingService.setClinic(updatedService.getClinic());
                    existingService.setName(updatedService.getName());
                    existingService.setDescription(updatedService.getDescription());

                    return serviceRepo.save(existingService);
                })
                .orElseThrow(() -> new RuntimeException(id) ); // Utilisation de l'exception personnalisée
    }
}

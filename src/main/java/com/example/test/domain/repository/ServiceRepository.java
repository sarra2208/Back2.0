package com.example.test.domain.repository;

import com.example.test.domain.model.Service;

import com.example.test.infrastructure.persistence.entity.ServiceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ServiceRepository {

    Service save(Service Service);

    Optional<Service> findServiceById(String id);


    ServiceEntity update(String id, Service updatedService);

    void deleteById(String id);

    List<Service> findAllServices();
}

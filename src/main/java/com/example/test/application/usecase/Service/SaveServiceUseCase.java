package com.example.test.application.usecase.Service;

import com.example.test.domain.model.Patient;
import com.example.test.domain.model.Service;
import com.example.test.domain.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class SaveServiceUseCase {
   private final ServiceRepository serviceRepository;
@Autowired
    public SaveServiceUseCase(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }



    public Service execute(Service service){
        return serviceRepository.save(service);
    }
    }


package com.example.test.application.service;

import com.example.test.application.usecase.Service.GetListServiceUseCase;
import com.example.test.application.usecase.Service.GetServiceUseCase;
import com.example.test.application.usecase.Service.SaveServiceUseCase;

import com.example.test.domain.model.Service;

import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Service
public class ServiceService {
    private final GetListServiceUseCase getListServiceUseCase;
    private final GetServiceUseCase getServiceUseCase;
    private final SaveServiceUseCase saveServiceUseCase;

    public ServiceService(GetListServiceUseCase getListServiceUseCase, GetServiceUseCase getServiceUseCase, SaveServiceUseCase saveServiceUseCase) {
        this.getListServiceUseCase = getListServiceUseCase;
        this.getServiceUseCase = getServiceUseCase;
        this.saveServiceUseCase = saveServiceUseCase;
    }

    public Service saveService(Service service) {
          return null;
    }

    public List<Service> getListServices(){
        return getListServiceUseCase.execute();
    }

    public Optional<Service> getServiceById(String id){
        return getServiceUseCase.execute(id);
    }


}

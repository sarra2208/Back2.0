package com.example.test.infrastructure.persistence.mapper;

import com.example.test.domain.model.Service;

import com.example.test.infrastructure.persistence.entity.ServiceEntity;


import static java.util.Objects.requireNonNull;

public class ServiceMapper {

    public static Service toDomain(ServiceEntity serviceEntity){
        requireNonNull(serviceEntity, "service entity should not be null!");
        return Service.builder()
                .id(serviceEntity.getId())
                .ClinicId(serviceEntity.getClinicId())
                .Name(serviceEntity.getName())
                .Description(serviceEntity.getDescription())
                .build();
    }

    public static ServiceEntity toEntity(Service service){
        requireNonNull(service, "service model should not be null!");
        return ServiceEntity.builder()
                .id(service.getId())
                .ClinicId(service.getClinicId())
                .Name(service.getName())
                .Description(service.getDescription())


                .build();
    }
    
    
    
}

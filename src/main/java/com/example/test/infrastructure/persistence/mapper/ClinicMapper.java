package com.example.test.infrastructure.persistence.mapper;

import com.example.test.domain.model.Clinic;
import com.example.test.infrastructure.persistence.entity.ClinicEntity;

import static java.util.Objects.requireNonNull;

public class ClinicMapper {

    public static Clinic toDomain(ClinicEntity clinicEntity) {
        requireNonNull(clinicEntity, "ClinicEntity should not be null!");
        return Clinic.builder()
                .id(clinicEntity.getId())
                .name(clinicEntity.getName())
                .address(clinicEntity.getAddress())
                .phone(clinicEntity.getPhone())
                .email(clinicEntity.getEmail())
                .build();
    }

    public static ClinicEntity toEntity(Clinic clinic) {
        requireNonNull(clinic, "Clinic model should not be null!");
        return ClinicEntity.builder()
                .id(clinic.getId())
                .name(clinic.getName())
                .address(clinic.getAddress())
                .phone(clinic.getPhone())
                .email(clinic.getEmail())
                .build();
    }
}

package com.example.test.infrastructure.persistence.mapper;

import com.example.test.domain.model.Prescription;
import com.example.test.infrastructure.persistence.entity.PrescriptionEntity;

import static java.util.Objects.requireNonNull;

public class PrescriptionMapper {
    public static Prescription toDomain(PrescriptionEntity PrescriptionEntity){
        requireNonNull(PrescriptionEntity, "Prescription entity should not be null!");
        return Prescription.builder()
                .id(PrescriptionEntity.getId())
                .date(PrescriptionEntity.getDate())


                .build();
    }

    public static PrescriptionEntity toEntity(Prescription Prescription){
        requireNonNull(Prescription, "Prescription model should not be null!");
        return PrescriptionEntity.builder()
                .id(Prescription.getId())
                .date(Prescription.getDate())


                .build();
    }
}

package com.example.test.infrastructure.persistence.mapper;

import com.example.test.domain.model.Consultation;
import com.example.test.domain.model.Consultation;
import com.example.test.infrastructure.persistence.entity.ConsultationEntity;
import com.example.test.infrastructure.persistence.entity.ConsultationEntity;

import static java.util.Objects.requireNonNull;

public class ConsultationMapper {
    public static Consultation toDomain(ConsultationEntity ConsultationEntity) {
        requireNonNull(ConsultationEntity, "ConsultationEntity should not be null!");
        return Consultation.builder()
                .id(ConsultationEntity.getId())
                .type(ConsultationEntity.getType())
                .note(ConsultationEntity.getNote())
                .status(ConsultationEntity.getStatus())
                
                .build();
    }
    public static ConsultationEntity toEntity(Consultation Consultation){
        requireNonNull(Consultation, "Consultation should not be null!");
        return ConsultationEntity.builder()
                .id(Consultation.getId())
                .type(Consultation.getType())
                .note(Consultation.getNote())
                .status(Consultation.getStatus())

                .build();
    }

}

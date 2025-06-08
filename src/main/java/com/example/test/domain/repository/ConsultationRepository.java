package com.example.test.domain.repository;

import com.example.test.domain.model.Consultation;
import com.example.test.infrastructure.persistence.entity.ConsultationEntity;

import java.util.List;
import java.util.Optional;

public interface ConsultationRepository {
    void deleteById(String id);

    Optional<Consultation> findConsultationById(String id);

    List<Consultation> findAllConsultations();

    Consultation save(Consultation consultation);
    ConsultationEntity update(String id, Consultation updatedConsultation);
}

package com.example.test.infrastructure.persistence.repo;

import com.example.test.domain.model.Consultation;
import com.example.test.domain.repository.ConsultationRepository;
import com.example.test.infrastructure.persistence.entity.ConsultationEntity;
import com.example.test.infrastructure.persistence.mapper.ConsultationMapper;

import com.example.test.infrastructure.persistence.repo.jpa.ConsultationRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConsultationRepoImpl implements ConsultationRepository {
    private final ConsultationRepo consultationRepo;

    public ConsultationRepoImpl(@Lazy ConsultationRepo consultationRepo) {
        this.consultationRepo = consultationRepo;

    }

    @Override
    public void deleteById(String id) {
        consultationRepo.deleteById(id);

    }

    @Override
    public Optional<Consultation> findConsultationById(String id) {
        return consultationRepo.findById(id)
                .map(ConsultationMapper::toDomain);
    }

    @Override
    public List<Consultation> findAllConsultations() {
        return consultationRepo.findAll()
                .stream()
                .map(ConsultationMapper::toDomain)
                .toList();
    }

    @Override
    public Consultation save(Consultation consultation) {
        return ConsultationMapper.toDomain( consultationRepo.save(ConsultationMapper.toEntity(consultation)));
    }

    @Override
    public ConsultationEntity update(String id, Consultation updatedConsultation) {
        return consultationRepo.findById(id)
                .map(existingConsultation -> {
                    existingConsultation.setType(updatedConsultation.getType());
                    existingConsultation.setStatus(updatedConsultation.getStatus());
                    existingConsultation.setNote(updatedConsultation.getNote());

                    return consultationRepo.save(existingConsultation);
                })
                .orElseThrow(() -> new RuntimeException(id) ); // Utilisation de l'exception personnalisée
    }
    }

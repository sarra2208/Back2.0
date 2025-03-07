package com.example.test.infrastructure.persistence.repo;

import com.example.test.domain.model.Patient;
import com.example.test.domain.repository.PatientRepository;
import com.example.test.infrastructure.persistence.mapper.PatientMapper;
import com.example.test.infrastructure.persistence.repo.jpa.PatientRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PatientRepoImpl implements PatientRepository {
    private final PatientRepo patientRepo;

    public PatientRepoImpl(@Lazy PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public Patient save(Patient patient) {
        return PatientMapper.toDomain( patientRepo.save(PatientMapper.toEntity(patient)));
    }

    @Override
    public List<Patient> findAllPatients() {
        return patientRepo.findAll()
                .stream()
                .map(PatientMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Patient> findPatientById(String id) {
        return patientRepo.findById(id)
                .map(PatientMapper::toDomain);
    }
}

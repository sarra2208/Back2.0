package com.example.test.infrastructure.persistence.repo;

import com.example.test.domain.model.Patient;
import com.example.test.domain.repository.PatientRepository;

import com.example.test.infrastructure.persistence.entity.PatientEntity;

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

    @Override
    public void deleteById(String id) {
        patientRepo.deleteById(id);
    }

    @Override
    public PatientEntity update(String id, Patient updatedPatient) {
        return patientRepo.findById(id)
                .map(existingPatient -> {
                    existingPatient.setFirstname(updatedPatient.getFirstname());
                    existingPatient.setLastname(updatedPatient.getLastname());
                    existingPatient.setBirthdate(updatedPatient.getBirthdate());
                    existingPatient.setGender(updatedPatient.getGender());
                    existingPatient.setMobile(updatedPatient.getMobile());
                    existingPatient.setEmail(updatedPatient.getEmail());
                    return patientRepo.save(existingPatient);
                })
                .orElseThrow(() -> new RuntimeException(id) ); // Utilisation de l'exception personnalisée
    }
}



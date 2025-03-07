package com.example.test.application.usecase;

import com.example.test.domain.model.Patient;
import com.example.test.domain.repository.PatientRepository;

import java.util.Optional;

public class GetPatientUseCase {
    private final PatientRepository patientRepository;

    public GetPatientUseCase(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Optional<Patient> execute(String id){
        return patientRepository.findPatientById(id);
    }
}

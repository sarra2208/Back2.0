package com.example.test.application.usecase;

import com.example.test.domain.model.Patient;
import com.example.test.domain.repository.PatientRepository;

public class SavePatientUseCase {
    private final PatientRepository patientRepository;

    public SavePatientUseCase(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient execute(Patient patient){
        return patientRepository.save(patient);
    }
}

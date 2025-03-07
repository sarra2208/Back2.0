package com.example.test.application.usecase;

import com.example.test.domain.model.Patient;
import com.example.test.domain.repository.PatientRepository;

import java.util.List;

public class GetListPatientsUseCase {
    private final PatientRepository patientRepository;

    public GetListPatientsUseCase(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> execute(){
        return patientRepository.findAllPatients();
    }
}

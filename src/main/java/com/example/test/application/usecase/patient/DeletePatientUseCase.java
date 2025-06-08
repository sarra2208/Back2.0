package com.example.test.application.usecase.patient;

import com.example.test.domain.repository.AppointmentRepository;
import com.example.test.domain.repository.PatientRepository;

public class DeletePatientUseCase {
    private final PatientRepository patientRepository;



    public DeletePatientUseCase(PatientRepository patientRepository) {
        this.patientRepository=patientRepository;
    }

    public  void execute(String id) {
        patientRepository.deleteById(id);
    }
}

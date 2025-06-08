package com.example.test.application.usecase.patient;



import com.example.test.domain.model.Patient;

import com.example.test.domain.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePatientUseCase {
    private final PatientRepository patientRepository;

    @Autowired
    public UpdatePatientUseCase(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient execute(String id, Patient updatedPatient) {
        return patientRepository.findPatientById(id)
                .map(existingPatient -> {
                    // Mettre à jour uniquement si l'ID existe
                    existingPatient.setFirstname(updatedPatient.getFirstname());
                    existingPatient.setLastname(updatedPatient.getLastname());
                    existingPatient.setBirthdate(updatedPatient.getBirthdate());
                    existingPatient.setGender(updatedPatient.getGender());
                    existingPatient.setMobile(updatedPatient.getMobile());
                    existingPatient.setEmail(updatedPatient.getEmail());

                    return patientRepository.save(existingPatient);
                })
                .orElseThrow(() -> new RuntimeException("⚠️ Appointment not found with ID: " + id));
    }
}
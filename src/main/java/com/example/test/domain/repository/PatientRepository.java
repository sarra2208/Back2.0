package com.example.test.domain.repository;

import com.example.test.domain.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    Patient save(Patient patient);
    List<Patient> findAllPatients();
    Optional<Patient> findPatientById(String id);

}

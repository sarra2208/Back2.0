package com.example.test.domain.repository;

import com.example.test.domain.model.Patient;

import org.springframework.stereotype.Repository;

import com.example.test.infrastructure.persistence.entity.PatientEntity;


import java.util.List;
import java.util.Optional;
@Repository
public interface PatientRepository {


     Optional<Patient> findPatientById(String id) ;

    Patient save(Patient patient);
    List<Patient> findAllPatients();



    void deleteById(String id);


   PatientEntity update(String id, Patient updatedPatient);



}

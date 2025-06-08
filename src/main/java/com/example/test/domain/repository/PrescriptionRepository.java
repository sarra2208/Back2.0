package com.example.test.domain.repository;

import com.example.test.domain.model.Patient;
import com.example.test.domain.model.Prescription;
import com.example.test.infrastructure.persistence.entity.PatientEntity;
import com.example.test.infrastructure.persistence.entity.PrescriptionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PrescriptionRepository {
    public void deleteById(String id) ;

    public List<Prescription> findAllPrescriptions() ;

    public Optional<Prescription> findPrescriptionById(String id) ;


    public Prescription save(Prescription prescription) ;
    PrescriptionEntity update(String id, Prescription updatedPrescription);

}

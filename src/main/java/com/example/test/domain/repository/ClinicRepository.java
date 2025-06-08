package com.example.test.domain.repository;

import com.example.test.domain.model.Clinic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ClinicRepository {
    



    void deleteById(String id);

    Optional<Clinic> findClinicById(String id);

    List<Clinic> findAllClinics();

    Clinic save(Clinic clinic);

    Clinic update(String id, Clinic updatedClinic);
}

package com.example.test.infrastructure.persistence.repo.jpa;

import com.example.test.infrastructure.persistence.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<PatientEntity, String> {

}

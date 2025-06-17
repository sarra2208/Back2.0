package com.example.test.infrastructure.persistence.repo.jpa;

import com.example.test.infrastructure.persistence.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientRepo extends JpaRepository<PatientEntity, String> {
    @Query("SELECT p FROM PatientEntity p WHERE p.email = :email")
    Optional<PatientEntity> findByEmail(@Param("email") String email);}


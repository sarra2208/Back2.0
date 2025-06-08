package com.example.test.domain.repository;

import com.example.test.domain.model.ElementPrescrit;
import com.example.test.domain.model.Patient;
import com.example.test.infrastructure.persistence.entity.ElementPrescritEntity;
import com.example.test.infrastructure.persistence.entity.PatientEntity;

import java.util.List;
import java.util.Optional;

public interface ElementPrescritRepository {
    void deleteById(String id);

    Optional<ElementPrescrit> findElementPrescritById(String id);

    List<ElementPrescrit> findAllelementPrescrits();

    ElementPrescrit save(ElementPrescrit elementPrescrit);
    ElementPrescritEntity update(String id, ElementPrescrit updatedElementPrescrit);
}

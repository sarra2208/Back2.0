package com.example.test.infrastructure.persistence.repo.jpa;

import com.example.test.infrastructure.persistence.entity.ElementPrescritEntity;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementPrescritRepo extends JpaRepository<ElementPrescritEntity , String > {
}

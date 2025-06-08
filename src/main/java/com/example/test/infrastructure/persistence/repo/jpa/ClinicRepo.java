package com.example.test.infrastructure.persistence.repo.jpa;


import com.example.test.infrastructure.persistence.entity.ClinicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepo extends JpaRepository<ClinicEntity,String> {

}

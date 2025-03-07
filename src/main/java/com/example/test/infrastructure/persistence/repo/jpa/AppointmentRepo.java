package com.example.test.infrastructure.persistence.repo.jpa;

import com.example.test.infrastructure.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<AppointmentEntity,Long>  {
}

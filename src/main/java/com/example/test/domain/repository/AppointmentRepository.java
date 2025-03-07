package com.example.test.domain.repository;

import com.example.test.domain.model.Appointment;
import com.example.test.domain.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);
    List<Appointment> findAllAppointment();
    Optional<Appointment> findAppointmentById(Long id);

}


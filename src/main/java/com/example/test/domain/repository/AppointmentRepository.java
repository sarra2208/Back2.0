package com.example.test.domain.repository;

import com.example.test.domain.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);
    List<Appointment> findAllAppointment();
    Optional<Appointment> findAppointmentById(Long id);

    void deleteById(Long id);

    Appointment update(Long id, Appointment updatedAppointment);
}



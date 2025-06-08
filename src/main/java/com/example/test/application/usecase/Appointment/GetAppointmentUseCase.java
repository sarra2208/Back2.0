package com.example.test.application.usecase.Appointment;

import com.example.test.domain.model.Appointment;


import com.example.test.domain.repository.AppointmentRepository;

import java.util.Optional;

public class GetAppointmentUseCase {
    private final AppointmentRepository appointmentRepository;

    public GetAppointmentUseCase(AppointmentRepository appointmentRepository) {
        this.appointmentRepository=appointmentRepository;
    }

    public Optional<Appointment> execute(Long id){

        return appointmentRepository.findAppointmentById(id);
    }
}


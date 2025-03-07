package com.example.test.application.usecase;

import com.example.test.domain.model.Appointment;
import com.example.test.domain.model.Patient;
import com.example.test.domain.repository.AppointmentRepository;
import com.example.test.domain.repository.PatientRepository;

public class SaveAppointmentUseCase {
    private final AppointmentRepository appointmentRepository;

    public SaveAppointmentUseCase(AppointmentRepository appointmentRepository) {
        this.appointmentRepository=appointmentRepository;
    }

    public Appointment execute(Appointment appointment){
        return appointmentRepository.save(appointment);
    }
}

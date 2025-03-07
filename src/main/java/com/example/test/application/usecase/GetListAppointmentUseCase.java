package com.example.test.application.usecase;

import com.example.test.domain.model.Appointment;
import com.example.test.domain.model.Patient;
import com.example.test.domain.repository.AppointmentRepository;
import com.example.test.domain.repository.PatientRepository;

import java.util.List;

public class GetListAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;

    public GetListAppointmentUseCase(AppointmentRepository appointmentRepository) {
        this.appointmentRepository=appointmentRepository;
    }

    public List<Appointment> execute(){
        return appointmentRepository.findAllAppointment();
    }
}

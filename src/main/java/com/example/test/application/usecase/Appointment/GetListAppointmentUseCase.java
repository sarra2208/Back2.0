package com.example.test.application.usecase.Appointment;
import com.example.test.domain.model.Appointment;
import com.example.test.domain.repository.AppointmentRepository;

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

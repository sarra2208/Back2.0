package com.example.test.application.service;

import com.example.test.application.usecase.*;
import com.example.test.domain.model.Appointment;
import com.example.test.domain.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class AppointmentService {
    private final SaveAppointmentUseCase saveAppointmentUseCase;
    private final GetListAppointmentUseCase getListAppointmentUseCase;
    private final GetAppointmentUseCase getAppointmentUseCase;

    public AppointmentService(SaveAppointmentUseCase saveAppointmentUseCase, GetListAppointmentUseCase getListAppointmentUseCase, GetAppointmentUseCase getAppointmentUseCase) {
        this.saveAppointmentUseCase = saveAppointmentUseCase;
        this.getListAppointmentUseCase = getListAppointmentUseCase;
        this.getAppointmentUseCase = getAppointmentUseCase;
    }

    public Appointment saveAppointment(Appointment appointment){
        return saveAppointmentUseCase.execute(appointment);
    }

    public List<Appointment> getListAppointments(){
        return getListAppointmentUseCase.execute();
    }

    public Optional<Appointment> getAppointmentById(Long id){
        return getAppointmentUseCase.execute(id);
    }
}

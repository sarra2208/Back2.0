package com.example.test.application.usecase.Appointment;

import com.example.test.domain.repository.AppointmentRepository;

public class DeleteAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;



   public DeleteAppointmentUseCase(AppointmentRepository appointmentRepository) {
       this.appointmentRepository = appointmentRepository;
   }

    public  void execute(Long id) {
        appointmentRepository.deleteById(id);
    }
}

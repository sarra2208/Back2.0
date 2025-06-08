package com.example.test.application.usecase.Appointment;

import com.example.test.domain.model.Appointment;
import com.example.test.domain.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateAppointmentUseCase {
    private final AppointmentRepository appointmentRepository;

    public UpdateAppointmentUseCase(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment execute(Long id, Appointment updatedAppointment) {
        return appointmentRepository.findAppointmentById(id)
                .map(existingAppointment -> {
                    // Mettre à jour uniquement si l'ID existe
                    existingAppointment.setDescription(updatedAppointment.getDescription());
                    existingAppointment.setNote(updatedAppointment.getNote());
                    existingAppointment.setState(updatedAppointment.getState());

                    return appointmentRepository.save(existingAppointment);
                })
                .orElseThrow(() -> new RuntimeException("⚠️ Appointment not found with ID: " + id));
    }
}


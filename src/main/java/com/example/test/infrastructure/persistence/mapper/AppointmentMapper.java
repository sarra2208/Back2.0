package com.example.test.infrastructure.persistence.mapper;

import com.example.test.domain.model.Appointment;


import com.example.test.infrastructure.persistence.entity.AppointmentEntity;
import com.example.test.infrastructure.persistence.repo.jpa.PatientRepo;
import com.example.test.infrastructure.persistence.repo.jpa.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;


import static java.util.Objects.requireNonNull;

public class AppointmentMapper {
    private StaffRepo staffRepo;
    private PatientRepo patientRepo;

    public static Appointment toDomain(AppointmentEntity appointmentEntity){
        requireNonNull(appointmentEntity, "appointment entity should not be null!");
        return Appointment.builder()
                .id(appointmentEntity.getId())
                .date(appointmentEntity.getDate())
                .heure(appointmentEntity.getHeure())
                .description(appointmentEntity.getDescription())
                .note(appointmentEntity.getNote())
                .state(appointmentEntity.getState())
                .build();
    }

    public static AppointmentEntity toEntity(Appointment appointment){
        requireNonNull(appointment, "appointment should not be null!");
        return AppointmentEntity.builder()
                .id(appointment.getId())
                .date(appointment.getDate())
                .heure(appointment.getHeure())
                .description(appointment.getDescription())
                .note(appointment.getNote())
                .state(appointment.getState())
                .build();
    }
}

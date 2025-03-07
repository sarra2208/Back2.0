package com.example.test.infrastructure.persistence.repo;

import com.example.test.domain.model.Appointment;
import com.example.test.domain.repository.AppointmentRepository;

import com.example.test.infrastructure.persistence.mapper.AppointmentMapper;

import com.example.test.infrastructure.persistence.repo.jpa.AppointmentRepo;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public  class AppointmentRepoImpl implements AppointmentRepository {
    private final AppointmentRepo appointmentRepo;

    public AppointmentRepoImpl(@Lazy AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }


    @Override
    public Appointment save(Appointment appointment) {
        return AppointmentMapper.toDomain( appointmentRepo.save(AppointmentMapper.toEntity(appointment)));
    }

    @Override
    public List<Appointment> findAllAppointment() {
        return appointmentRepo.findAll()
                .stream()
                .map(AppointmentMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Appointment> findAppointmentById(Long id) {
        return appointmentRepo.findById(id)
                .map(AppointmentMapper::toDomain);
    }
}

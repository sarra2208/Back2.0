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
        return AppointmentMapper.toDomain(appointmentRepo.save(AppointmentMapper.toEntity(appointment)));
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

    @Override
    public void deleteById(Long id) {
        appointmentRepo.deleteById(id);
    }


    public Optional<Appointment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Appointment update(Long id, Appointment updatedAppointment) {
        return appointmentRepo.findById(id)
                .map(existingAppointment -> {
                    existingAppointment.setDate(updatedAppointment.getDate());
                    existingAppointment.setHeure(updatedAppointment.getHeure());
                    existingAppointment.setDescription(updatedAppointment.getDescription());
                    existingAppointment.setNote(updatedAppointment.getNote());
                    existingAppointment.setState(updatedAppointment.getState());
                    return AppointmentMapper.toDomain(appointmentRepo.save(existingAppointment));
                })
                .orElseThrow(() -> new RuntimeException("Appointment with id " + id + " not found"));


}}


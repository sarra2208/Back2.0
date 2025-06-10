package com.example.test.web.controller;


import com.example.test.infrastructure.persistence.repo.jpa.AppointmentRepo;
import com.example.test.infrastructure.persistence.repo.jpa.ClinicRepo;
import com.example.test.infrastructure.persistence.repo.jpa.PatientRepo;
import com.example.test.infrastructure.persistence.repo.jpa.StaffRepo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v0/stats")
@CrossOrigin
public class StatsController {
    private final PatientRepo patientRepo;
    private final StaffRepo staffRepo;
    private final ClinicRepo clinicRepo;
    private final AppointmentRepo appointmentRepo;

    public StatsController(PatientRepo patientRepo,StaffRepo staffRepo,ClinicRepo clinicRepo,AppointmentRepo appointmentRepo){
        this.patientRepo=patientRepo;
        this.staffRepo=staffRepo;
        this.clinicRepo=clinicRepo;
        this.appointmentRepo=appointmentRepo;
    }
    @GetMapping("/recent")
    public Stats getRecentStat(){
        Stats stat = new Stats(this.staffRepo.count(),this.patientRepo.count(),this.clinicRepo.count(),this.appointmentRepo.count());
        stat.setNumberPatient(this.patientRepo.count());
        stat.setNumberStuff(this.staffRepo.count());
        stat.setNumberAppointments(this.appointmentRepo.count());
        stat.setNumberClinics(this.clinicRepo.count());
        return stat;
    }

}

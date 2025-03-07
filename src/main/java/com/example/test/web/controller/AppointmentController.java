package com.example.test.web.controller;

import com.example.test.application.service.AppointmentService;
import com.example.test.application.service.PatientService;
import com.example.test.domain.model.Appointment;
import com.example.test.domain.model.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v0")
@CrossOrigin
public class AppointmentController {


    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/addAppointment")
    public Appointment saveAppointment(@RequestBody Appointment appointment){
        return appointmentService.saveAppointment(appointment );
    }
    @GetMapping("/appointments")
    public List<Appointment> getListAppointments(){
        return appointmentService.getListAppointments();
    }

    @GetMapping("/{id}")
    public Optional<Appointment> getAppointment(@PathVariable Long id){
        return appointmentService.getAppointmentById(id);
    }
}

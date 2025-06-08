package com.example.test.web.controller;

import com.example.test.application.service.AppointmentService;
import com.example.test.application.usecase.Appointment.DeleteAppointmentUseCase;
import com.example.test.application.usecase.Appointment.UpdateAppointmentUseCase;
import com.example.test.domain.model.Appointment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v0/appointment")
@CrossOrigin
public class AppointmentController {


    private final AppointmentService appointmentService;
    private final DeleteAppointmentUseCase deleteAppointmentUseCase;
    private final UpdateAppointmentUseCase updateAppointmentUseCase;

    public AppointmentController(AppointmentService appointmentService,DeleteAppointmentUseCase deleteAppointmentUseCase,UpdateAppointmentUseCase updateAppointmentUseCase) {
        this.appointmentService = appointmentService;
        this.deleteAppointmentUseCase=deleteAppointmentUseCase;
        this.updateAppointmentUseCase=updateAppointmentUseCase;
    }

    @PostMapping("/addAppointment")
    public Appointment saveAppointment(@RequestBody Appointment appointment) {
        return appointmentService.saveAppointment(appointment);
    }

    @GetMapping("/listAppointments")
    public List<Appointment> getListAppointments() {
        return appointmentService.getListAppointments();
    }

    @GetMapping("/{id}")
    public Optional<Appointment> getAppointment(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }



        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteAppointment (@PathVariable Long id){

            deleteAppointmentUseCase.execute(id); // ✅ Correction de l'appel
            return ResponseEntity.noContent().build();
        }


        @PutMapping("/{id}")
        public ResponseEntity<?> updateAppointment (@PathVariable Long id, @RequestBody Appointment appointment){
            try {
                Appointment updatedAppointment = updateAppointmentUseCase.execute(id, appointment);
                return ResponseEntity.ok(updatedAppointment);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage()); // Retourne un message clair si l'ID n'existe pas
            }
        }
    }

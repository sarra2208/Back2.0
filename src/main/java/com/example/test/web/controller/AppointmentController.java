package com.example.test.web.controller;

import com.example.test.application.service.AppointmentService;
import com.example.test.application.usecase.Appointment.DeleteAppointmentUseCase;
import com.example.test.application.usecase.Appointment.UpdateAppointmentUseCase;
import com.example.test.domain.model.Appointment;
import com.example.test.infrastructure.persistence.entity.AppointementDao;
import com.example.test.infrastructure.persistence.entity.AppointmentEntity;
import com.example.test.infrastructure.persistence.entity.AppointmentStatsDTO;
import com.example.test.infrastructure.persistence.entity.ReminderRequest;
import com.example.test.infrastructure.persistence.repo.jpa.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v0/appointment")
@CrossOrigin
public class AppointmentController {
    @Autowired
     private AppointmentRepo appointmentRepo;
    private final AppointmentService appointmentService;
    private final DeleteAppointmentUseCase deleteAppointmentUseCase;
    private final UpdateAppointmentUseCase updateAppointmentUseCase;


    public AppointmentController(AppointmentService appointmentService,DeleteAppointmentUseCase deleteAppointmentUseCase,UpdateAppointmentUseCase updateAppointmentUseCase) {
        this.appointmentService = appointmentService;
        this.deleteAppointmentUseCase=deleteAppointmentUseCase;
        this.updateAppointmentUseCase=updateAppointmentUseCase;
    }

    @GetMapping("/next/{patientId}")
    public ResponseEntity<AppointmentEntity> getNextAppointment(@PathVariable String patientId) {
        AppointmentEntity nextAppointment = appointmentService.findNextAppointmentByPatientId(patientId);

        if (nextAppointment == null) {
            return ResponseEntity.noContent().build(); // 204 No Content if no appointment found
        }

        // Convert Appointment entity to DT

        return ResponseEntity.ok(nextAppointment);
    }


    @PostMapping("/addAppointment")
    public AppointmentEntity saveAppointment(@RequestBody AppointementDao appointment) {
        return appointmentService.saveAppointmentDao(appointment);
    }

    @GetMapping("/listAppointments")
    public List<AppointmentEntity> getListAppointments() {
        return appointmentRepo.findAll();
    }
    @GetMapping("/monthly-stats")
    public List<AppointmentStatsDTO> getMonthlyAppointmentStats() {
        return appointmentRepo.countAppointmentsByMonth();
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


    @PostMapping("/send-reminder")
    public ResponseEntity<?> sendReminderEmail(@RequestBody ReminderRequest request) {
     return appointmentService.sendReminder(request.getAppointment(),request.getEmail());
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

package com.example.test.application.service;

import com.example.test.application.usecase.Appointment.GetAppointmentUseCase;
import com.example.test.application.usecase.Appointment.GetListAppointmentUseCase;
import com.example.test.application.usecase.Appointment.SaveAppointmentUseCase;
import com.example.test.domain.model.Appointment;

import com.example.test.infrastructure.persistence.entity.AppointementDao;
import com.example.test.infrastructure.persistence.entity.AppointmentEntity;
import com.example.test.infrastructure.persistence.entity.PatientEntity;
import com.example.test.infrastructure.persistence.repo.jpa.AppointmentRepo;
import com.example.test.infrastructure.persistence.repo.jpa.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class AppointmentService {
    @Autowired
    private JavaMailSender mailSender;
    private final SaveAppointmentUseCase saveAppointmentUseCase;
    private final GetListAppointmentUseCase getListAppointmentUseCase;
    private final GetAppointmentUseCase getAppointmentUseCase;
    @Autowired
    private  PatientRepo patientRepo;
    @Autowired
    private  AppointmentRepo appointmentRepo ;

    public AppointmentService(SaveAppointmentUseCase saveAppointmentUseCase, GetListAppointmentUseCase getListAppointmentUseCase, GetAppointmentUseCase getAppointmentUseCase ) {
        this.saveAppointmentUseCase = saveAppointmentUseCase;
        this.getListAppointmentUseCase = getListAppointmentUseCase;
        this.getAppointmentUseCase = getAppointmentUseCase;

    }

    public AppointmentEntity findNextAppointmentByPatientId(String patientId) {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        String todayDate = today.format(DateTimeFormatter.ISO_DATE);  // "2025-06-17"
        String currentTime = now.format(DateTimeFormatter.ofPattern("HH:mm"));  // "14:30"

        List<AppointmentEntity> results = appointmentRepo.findNextAppointmentByPatientId(
                patientId, todayDate, currentTime);

        return results.isEmpty() ? null : results.get(0);
    }
    public AppointmentEntity saveAppointmentDao(AppointementDao appointment) {
        // return saveAppointmentUseCase.execute(appointment);
        Optional<PatientEntity> patient = patientRepo.findByEmail(appointment.getPatient());
        AppointmentEntity ap = new AppointmentEntity();
        ap.setDate(appointment.getDate());
        ap.setPatient(patient.orElse(null));
        ap.setState(appointment.getState());
        ap.setDescription(appointment.getDescription());
        ap.setHeure(appointment.getHeure());
        ap.setNote(appointment.getNote());
        ap.setStaff(appointment.getStaff());
        return appointmentRepo.save(ap);
    }


    public Appointment saveAppointment(Appointment appointment) {
        return saveAppointmentUseCase.execute(appointment);
    }

    public List<Appointment> getListAppointments() {
        return getListAppointmentUseCase.execute();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return getAppointmentUseCase.execute(id);
    }

    public ResponseEntity<?> sendReminder(AppointmentEntity appointment, String email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Appointment Reminder");
            message.setText("Dear patient, this is a reminder for your appointment on "
                    + appointment.getDate()
                    + ". Details: " + appointment.getDate());
            mailSender.send(message);

            return ResponseEntity.ok().body("{\"success\":true}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"error\":\"Failed to send email\"}");
        }
    }
    @Scheduled(cron = "0 0 8 * * *") // every day at 08:00 AM
    public void sendDailyAppointmentReminders() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        List<AppointmentEntity> appointments = appointmentRepo
                .findByDate(tomorrow.toString());

        for (AppointmentEntity appointment : appointments) {
            if (appointment.getPatient() != null && appointment.getPatient().getEmail() != null) {
                sendReminder(appointment, appointment.getPatient().getEmail());
            }
        }

    }
}

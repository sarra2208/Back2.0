package com.example.test.infrastructure.persistence.entity;

public class ReminderRequest {
    private String email;
    private AppointmentEntity appointment;

    public ReminderRequest(String email, AppointmentEntity appointment) {
        this.email = email;
        this.appointment = appointment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AppointmentEntity getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentEntity appointment) {
        this.appointment = appointment;
    }
// getters and setters
}
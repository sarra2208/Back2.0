package com.example.test.infrastructure.persistence.entity;

public class AppointementDao {
    private int id;
    private String date;
    private String heure;
    private String description;
    private String note;
    private String state;
    private StaffEntity staff;
    private String patient;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getHeure() { return heure; }
    public void setHeure(String heure) { this.heure = heure; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public StaffEntity getStaff() { return staff; }
    public void setStaff(StaffEntity staff) { this.staff = staff; }

    public String getPatient() { return patient; }
    public void setPatient(String patient) { this.patient = patient; }

}

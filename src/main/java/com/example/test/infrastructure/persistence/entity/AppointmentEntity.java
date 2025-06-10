package com.example.test.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentEntity {
    @Id

    private Long id ;
    private Date date ;
    private String heure ;
    private String description;
    private String note ;
    private String state ;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity staff;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;


}

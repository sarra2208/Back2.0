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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String date ;
    private String heure ;
    private String description;
    private String note ;
    private String state ;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffEntity staff;

    @ManyToOne(optional = true)
    @JoinColumn(name = "patient_id",nullable = true)
    private PatientEntity patient;


}

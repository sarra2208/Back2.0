package com.example.test.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffEntity {
    @Id
    private String id;
    private String nom;
    private String   prenom;
    private String role;
    private String       email;
    private String telephone;
    private String   adresse;
    private LocalDate hireDate;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;
    @JsonIgnore
    @OneToMany(mappedBy = "staff")
    private List<AppointmentEntity> appointments;
}

package com.example.test.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
}

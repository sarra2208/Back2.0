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
public class PatientEntity {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String gender;
    private String mobile;
    private String email;
}

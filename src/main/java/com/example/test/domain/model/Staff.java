package com.example.test.domain.model;

import com.example.test.infrastructure.persistence.entity.AppointmentEntity;
import com.example.test.infrastructure.persistence.entity.ServiceEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private Long id;
    private String nom;
    private String prenom;
    private String role;
    private String email;
    private String telephone;
    private String adresse;
    private LocalDate hireDate;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity service;
    @JsonIgnore
    @OneToMany(mappedBy = "staff")
    private List<AppointmentEntity> appointments;
}



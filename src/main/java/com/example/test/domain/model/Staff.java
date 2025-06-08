package com.example.test.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class Staff {
    private String id;
    private String nom;
    private String prenom;
    private String role;
    private String email;
    private String telephone;
    private String adresse;
    private LocalDate hireDate;
}



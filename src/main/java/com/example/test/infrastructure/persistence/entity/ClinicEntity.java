package com.example.test.infrastructure.persistence.entity;

import com.example.test.domain.model.Service;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClinicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name;
    private String address ;
    private String phone ;
    private String email ;
    @JsonIgnore
    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<ServiceEntity> services;
}

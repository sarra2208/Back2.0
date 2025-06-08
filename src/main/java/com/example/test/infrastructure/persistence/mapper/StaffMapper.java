package com.example.test.infrastructure.persistence.mapper;

import com.example.test.domain.model.Staff;
import com.example.test.domain.model.Staff;
import com.example.test.infrastructure.persistence.entity.StaffEntity;
import com.example.test.infrastructure.persistence.entity.StaffEntity;

import static java.util.Objects.requireNonNull;

public class StaffMapper {
    public static Staff toDomain(StaffEntity staffEntity){
        requireNonNull(staffEntity, "staff entity should not be null!");
        return Staff.builder()
                .id(staffEntity.getId())
                .nom(staffEntity.getNom())
                .prenom(staffEntity.getPrenom())
                .role(staffEntity.getRole())
                .email(staffEntity.getEmail())
                .telephone(staffEntity.getTelephone())
                .adresse(staffEntity.getAdresse())
                .hireDate(staffEntity.getHireDate())
                .build();
    }
    public static StaffEntity toEntity(Staff staff){
        requireNonNull(staff, "staff model should not be null!");
        return StaffEntity.builder()
                .id(staff.getId())
                .nom(staff.getNom())
                .prenom(staff.getPrenom())
                .role(staff.getRole())
                .email(staff.getEmail())
                .telephone(staff.getTelephone())
                .adresse(staff.getAdresse())
                .hireDate(staff.getHireDate())


                .build();
}}

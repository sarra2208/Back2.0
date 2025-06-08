package com.example.test.infrastructure.persistence.mapper;

import com.example.test.domain.model.ElementPrescrit;
import com.example.test.infrastructure.persistence.entity.ElementPrescritEntity;

import static java.util.Objects.requireNonNull;

public class ElementPrescritMapper {
    public static ElementPrescrit toDomain(ElementPrescritEntity ElementPrescritEntity){
        requireNonNull(ElementPrescritEntity, "ElementPrescrit entity should not be null!");
        return ElementPrescrit.builder()
                .id(ElementPrescritEntity.getId())
                .type(ElementPrescritEntity.getType())
                .nom(ElementPrescritEntity.getNom())
                .description(ElementPrescritEntity.getDescription())

                .build();
    }

    public static ElementPrescritEntity toEntity(ElementPrescrit ElementPrescrit){
        requireNonNull(ElementPrescrit, "ElementPrescrit model should not be null!");
        return ElementPrescritEntity.builder()
                .id(ElementPrescrit.getId())
                .type(ElementPrescrit.getType())
                .nom(ElementPrescrit.getNom())
                .description(ElementPrescrit.getDescription())

                .build();
    }
}

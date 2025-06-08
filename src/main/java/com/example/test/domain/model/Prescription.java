package com.example.test.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class Prescription {
    private String id ;
    private LocalDate date;


}

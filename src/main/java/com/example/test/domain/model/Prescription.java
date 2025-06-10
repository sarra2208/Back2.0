package com.example.test.domain.model;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
    private String id ;
    private LocalDate date;


}

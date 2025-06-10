package com.example.test.web.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Stats {
    public Long numberStuff;
    public Long numberPatient;
    public Long numberClinics;
    public Long numberAppointments;
}

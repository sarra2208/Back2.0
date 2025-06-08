package com.example.test.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Service {
    private String id;
    private String ClinicId;

    private String Name;
    private String Description;
}

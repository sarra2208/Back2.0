package com.example.test.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Appointment {
 private Long id ;
 private String description;
 private String note ;
 private String state ;


}

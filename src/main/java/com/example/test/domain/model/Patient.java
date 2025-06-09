package com.example.test.domain.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class Patient {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String gender;
    private String mobile;
    private String email;




}

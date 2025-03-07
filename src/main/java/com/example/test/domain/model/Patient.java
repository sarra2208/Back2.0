package com.example.test.domain.model;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class Patient {
    private String id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String gender;
    private String mobile;
    private String email;

}

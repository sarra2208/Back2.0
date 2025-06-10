package com.example.test.domain.model;


import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String gender;
    private String mobile;
    private String email;




}

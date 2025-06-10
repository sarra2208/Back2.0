package com.example.test.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {


    private Long id ;
    private String name;
    private String address ;
    private String phone ;
    private String email ;



}

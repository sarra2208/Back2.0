package com.example.test.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Clinic {


    private Long id ;
    private String name;
    private String address ;
    private String phone ;
    private String email ;



}

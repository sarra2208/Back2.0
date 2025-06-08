package com.example.test.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class ElementPrescrit {

    private String id ;
    private String type ;
    private String nom ;
    private String description ;
}

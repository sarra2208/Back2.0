package com.example.test.domain.model;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ElementPrescrit {

    private String id ;
    private String type ;
    private String nom ;
    private String description ;
}

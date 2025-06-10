package com.example.test.domain.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    private Long id;
    private Clinic Clinic;
    private String Name;
    private String Description;
}

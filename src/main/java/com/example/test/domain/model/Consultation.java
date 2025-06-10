package com.example.test.domain.model;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consultation {
    @Setter
    private String id;
    private String type;
    private String status;
    private String note;

    public void setType(String type) {
    }

    public void setStatus(String note) {
    }

    public void setNote(String status) {
    }
}
package com.example.test.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder

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
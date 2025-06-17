package com.example.test.infrastructure.persistence.entity;

import java.util.List;

public class PrescriptionRequest {
    private String email;
    private List<String> items;

    // Getters & setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<String> getItems() { return items; }
    public void setItems(List<String> items) { this.items = items; }
}

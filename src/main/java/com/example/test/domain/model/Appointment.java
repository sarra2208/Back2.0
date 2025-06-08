package com.example.test.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class Appointment {
 private Long id ;
 private Date date ;
 private String heure ;
 private String description;
 private String note ;
 private String state ;



 public void setDescription(String description) {
  this.description=description;
 }

 public void setNote(String note) {
  this.note = note;
 }

 public void setState(String state) {
  this.state = state;
 }

}

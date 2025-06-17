package com.example.test.domain.model;

import com.example.test.infrastructure.persistence.entity.StaffEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
 private Long id ;
 private String date ;
 private String heure ;
 private String description;
 private String note ;
 private String state ;
 private String staff;
 private String patient;



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

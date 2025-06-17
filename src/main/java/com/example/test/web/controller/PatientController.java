package com.example.test.web.controller;

import com.example.test.application.service.PatientService;
import com.example.test.application.usecase.patient.DeletePatientUseCase;
import com.example.test.application.usecase.patient.UpdatePatientUseCase;
import com.example.test.domain.model.Patient;
import com.example.test.infrastructure.persistence.entity.PrescriptionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v0/patient")
@CrossOrigin
public class PatientController {
    private final PatientService patientService;
    private final DeletePatientUseCase deletePatientUseCase;
    private final UpdatePatientUseCase updatePatientUseCase;

    public PatientController(PatientService patientService, DeletePatientUseCase deletePatientUseCase, UpdatePatientUseCase updatePatientUseCase) {
        this.patientService = patientService;
        this.deletePatientUseCase=deletePatientUseCase;
        this.updatePatientUseCase=updatePatientUseCase;

    }
    @PostMapping("/sendPR")
    public ResponseEntity<?> sendPrescription(@RequestBody PrescriptionRequest request) {
        try {
            patientService.sendPrescriptionEmail(request.getEmail(), request.getItems());
            return ResponseEntity.ok().body("{\"success\":true}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"error\":\"Failed to send prescription email\"}");
        }
    }
    @PostMapping("/addPatient")
    public Patient savePatient(@RequestBody Patient patient){
        return patientService.savePatient(patient);
    }

    @GetMapping("/listPatients")
    public List<Patient> getListPatients(){
        return patientService.getListPatients();
    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatient(@PathVariable String id){
        return patientService.getPatientById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        deletePatientUseCase.execute(id); // ✅ Correction de l'appel
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient (@PathVariable String id, @RequestBody Patient patient){
        try {
            Patient updatedPatient = updatePatientUseCase.execute(id, patient);
            return ResponseEntity.ok(updatedPatient);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Retourne un message clair si l'ID n'existe pas
        }
    }





}

package com.example.test.web.controller;

import com.example.test.application.service.ClinicService;
import com.example.test.application.usecase.Clinic.DeleteClinicUseCase;
import com.example.test.application.usecase.Clinic.UpdateClinicUseCase;

import com.example.test.domain.model.Clinic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v0/clinic")
@CrossOrigin
public class ClinicController {
    private final ClinicService clinicService;
    private final DeleteClinicUseCase deleteClinicUseCase;
    private final UpdateClinicUseCase updateClinicUseCase;


    public ClinicController(ClinicService clinicService, DeleteClinicUseCase deleteClinicUseCase, UpdateClinicUseCase updateClinicUseCase) {
        this.clinicService = clinicService;
        this.deleteClinicUseCase = deleteClinicUseCase;
        this.updateClinicUseCase = updateClinicUseCase;
    }
    @PostMapping("/addClinic")
    public Clinic saveClinic(@RequestBody Clinic clinic) {
        return clinicService.saveClinic(clinic);
    }

    @GetMapping("/listClinics")
    public List<Clinic> getListClinic() {
        return clinicService.getListClinics();
    }

    @GetMapping("/{id}")
    public Optional<Clinic> getClinic(@PathVariable String id) {
        return clinicService.getClinicById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinic (@PathVariable String id){

        deleteClinicUseCase.execute(id); // ✅ Correction de l'appel
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateClinic(@PathVariable String id, @RequestBody Clinic  clinic){
        try {
           Clinic updatedClinic = updateClinicUseCase.execute(id, clinic);
            return ResponseEntity.ok(updatedClinic);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Retourne un message clair si l'ID n'existe pas
        }
    }
}

package com.example.test.web.controller;



import com.example.test.application.service.ConsultationService;
import com.example.test.application.usecase.Consultation.DeleteConsultationUseCase;
import com.example.test.application.usecase.Consultation.UpdateConsultationUseCase;
import com.example.test.domain.model.Consultation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v0/consultation")
@CrossOrigin
public class ConsultationController {
    private final ConsultationService consultationService;
    private final DeleteConsultationUseCase deleteConsultationUseCase;
    private final UpdateConsultationUseCase updateConsultationUseCase;

    public ConsultationController(ConsultationService ConsultationService, DeleteConsultationUseCase deleteConsultationUseCase, UpdateConsultationUseCase updateConsultationUseCase) {
        this.consultationService= ConsultationService;
        this.deleteConsultationUseCase=deleteConsultationUseCase;
        this.updateConsultationUseCase=updateConsultationUseCase;

    }

    @PostMapping("/addConsultation")
    public Consultation saveConsultation(@RequestBody Consultation Consultation){
        return consultationService.saveConsultation(Consultation);
    }

    @GetMapping("/listConsultations")
    public List<Consultation> getListConsultations(){
        return consultationService.getListConsultations();
    }

    @GetMapping("/{id}")
    public Optional<Consultation> getConsultation(@PathVariable String id){
        return consultationService.getConsultationById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable String id) {
        deleteConsultationUseCase.execute(id); // ✅ Correction de l'appel
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateConsultation (@PathVariable String id, @RequestBody Consultation Consultation){
        try {
            Consultation updatedConsultation = updateConsultationUseCase.execute(id, Consultation);
            return ResponseEntity.ok(updatedConsultation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Retourne un message clair si l'ID n'existe pas
        }
    }
}


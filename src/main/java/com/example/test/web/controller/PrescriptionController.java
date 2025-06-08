package com.example.test.web.controller;


import com.example.test.application.service.PrescriptionService;
import com.example.test.application.usecase.Prescription.DeletePrescriptionUseCase;
import com.example.test.application.usecase.Prescription.UpdatePrescriptionUseCase;
import com.example.test.domain.model.Prescription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v0/prescription")
@CrossOrigin
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final DeletePrescriptionUseCase deletePrescriptionUseCase;
    private final UpdatePrescriptionUseCase updatePrescriptionUseCase;

    public PrescriptionController(PrescriptionService PrescriptionService, PrescriptionService prescriptionService, DeletePrescriptionUseCase deletePrescriptionUseCase, UpdatePrescriptionUseCase updatePrescriptionUseCase) {
        this.prescriptionService = prescriptionService;

        this.deletePrescriptionUseCase=deletePrescriptionUseCase;
        this.updatePrescriptionUseCase=updatePrescriptionUseCase;

    }

    @PostMapping("/addPrescription")
    public Prescription savePrescription(@RequestBody Prescription Prescription){
        return prescriptionService.savePrescription(Prescription);
    }

    @GetMapping("/listPrescriptions")
    public List<Prescription> getListPrescriptions(){
        return prescriptionService.getListPrescriptions();
    }

    @GetMapping("/{id}")
    public Optional<Prescription> getPrescription(@PathVariable String id){
        return prescriptionService.getPrescriptionById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable String id) {
        deletePrescriptionUseCase.execute(id); // ✅ Correction de l'appel
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePrescription (@PathVariable String id, @RequestBody Prescription Prescription){
        try {
            Prescription updatedPrescription = updatePrescriptionUseCase.execute(id, Prescription);
            return ResponseEntity.ok(updatedPrescription);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Retourne un message clair si l'ID n'existe pas
        }
    }

}

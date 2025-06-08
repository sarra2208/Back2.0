package com.example.test.application.usecase.Prescription;

import com.example.test.domain.model.Prescription;
import com.example.test.domain.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePrescriptionUseCase {
    private final PrescriptionRepository prescriptionRepository;
@Autowired

    public UpdatePrescriptionUseCase(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }
    public Prescription execute(String id, Prescription updatedPrescription) {
        return prescriptionRepository.findPrescriptionById(id)
                .map(existingPrescription -> {
                    // Mettre à jour uniquement si l'ID existe
                    existingPrescription.setDate(updatedPrescription.getDate());



                    return prescriptionRepository.save(existingPrescription);
                })
                .orElseThrow(() -> new RuntimeException("⚠️ Appointment not found with ID: " + id));
    }
}

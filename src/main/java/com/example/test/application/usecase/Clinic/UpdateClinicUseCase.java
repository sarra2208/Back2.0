package com.example.test.application.usecase.Clinic;

import com.example.test.domain.model.Clinic;
import com.example.test.domain.model.Patient;
import com.example.test.domain.repository.ClinicRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateClinicUseCase {
    final private ClinicRepository clinicRepository;

    public UpdateClinicUseCase(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }
    public Clinic execute(String id, Clinic updatedClinic) {
        return clinicRepository.findClinicById(id)
                .map(existingClinic -> {
                    // Mettre à jour uniquement si l'ID existe
                    existingClinic.setName(updatedClinic.getName());
                    existingClinic.setAddress(updatedClinic.getAddress());
                    existingClinic.setPhone(updatedClinic.getPhone());
                    existingClinic.setEmail(updatedClinic.getEmail());


                    return clinicRepository.save(existingClinic);
                })
                .orElseThrow(() -> new RuntimeException("⚠️ Appointment not found with ID: " + id));
    }
}

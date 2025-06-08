package com.example.test.application.usecase.Staff;

import com.example.test.domain.model.Staff;
import com.example.test.domain.model.Staff;
import com.example.test.domain.repository.StaffRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateStaffUseCase {
   private final StaffRepository staffRepository;

    public UpdateStaffUseCase(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }
    public Staff execute(String id, Staff updatedStaff) {
        return staffRepository.findStaffById(id)
                .map(existingStaff -> {
                    // Mettre à jour uniquement si l'ID existe
                    existingStaff.setNom(updatedStaff.getNom());
                    existingStaff.setPrenom(updatedStaff.getPrenom());
                    existingStaff.setRole(updatedStaff.getRole());
                    existingStaff.setEmail(updatedStaff.getEmail());
                    existingStaff.setTelephone(updatedStaff.getTelephone());
                    existingStaff.setAdresse(updatedStaff.getAdresse());
                    existingStaff.setHireDate(updatedStaff.getHireDate());


                    return staffRepository.save(existingStaff);
                })
                .orElseThrow(() -> new RuntimeException("⚠️ Appointment not found with ID: " + id));
}}

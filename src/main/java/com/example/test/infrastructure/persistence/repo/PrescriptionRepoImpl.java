package com.example.test.infrastructure.persistence.repo;

import com.example.test.domain.model.Prescription;
import com.example.test.domain.repository.PrescriptionRepository;
import com.example.test.infrastructure.persistence.entity.PrescriptionEntity;
import com.example.test.infrastructure.persistence.mapper.PrescriptionMapper;
import com.example.test.infrastructure.persistence.mapper.PrescriptionMapper;
import com.example.test.infrastructure.persistence.repo.jpa.PrescriptionRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class PrescriptionRepoImpl implements PrescriptionRepository {
    
    private final PrescriptionRepo prescriptionRepo;

    public PrescriptionRepoImpl(@Lazy PrescriptionRepo prescriptionRepo) {
        this.prescriptionRepo = prescriptionRepo;
    }

    @Override
    public void deleteById(String id) {
        prescriptionRepo.deleteById(id);
    }

    @Override
    public List<Prescription> findAllPrescriptions() {
        return prescriptionRepo.findAll()
                .stream()
                .map(PrescriptionMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Prescription> findPrescriptionById(String id) {
        return prescriptionRepo.findById(id)
                .map(PrescriptionMapper::toDomain);
    }

    @Override
    public Prescription save(Prescription prescription) {
        return PrescriptionMapper.toDomain( prescriptionRepo.save(PrescriptionMapper.toEntity(prescription)));
    }

    @Override
    public PrescriptionEntity update(String id, Prescription updatedPrescription) {
        return prescriptionRepo.findById(id)
                .map(existingPrescription -> {
                    existingPrescription.setDate(updatedPrescription.getDate());


                    return prescriptionRepo.save(existingPrescription);
                })
                .orElseThrow(() -> new RuntimeException(id) ); // Utilisation de l'exception personnalisée
    }
}

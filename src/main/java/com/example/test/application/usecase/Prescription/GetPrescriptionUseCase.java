package com.example.test.application.usecase.Prescription;

import com.example.test.domain.model.Prescription;
import com.example.test.domain.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GetPrescriptionUseCase {
    private final PrescriptionRepository prescriptionRepository;

    public GetPrescriptionUseCase(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }
    public Optional<Prescription> execute(String id){
        return prescriptionRepository.findPrescriptionById(id);
    }
}

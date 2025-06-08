package com.example.test.application.usecase.Prescription;

import com.example.test.domain.repository.PrescriptionRepository;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Service;

@Service
public class DeletePrescriptionUseCase {
    private final PrescriptionRepository prescriptionRepository;

    public DeletePrescriptionUseCase(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public  void execute(String id) {
        prescriptionRepository.deleteById(id);
    }
}

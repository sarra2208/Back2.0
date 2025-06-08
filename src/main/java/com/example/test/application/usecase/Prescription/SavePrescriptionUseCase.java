package com.example.test.application.usecase.Prescription;
import com.example.test.domain.model.Prescription;
import com.example.test.domain.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class SavePrescriptionUseCase {
   private final PrescriptionRepository prescriptionRepository;

    public SavePrescriptionUseCase(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }
    public Prescription execute(Prescription prescription){
        return prescriptionRepository.save(prescription);
    }
}

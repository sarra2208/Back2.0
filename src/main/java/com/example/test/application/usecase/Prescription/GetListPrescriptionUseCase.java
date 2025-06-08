package com.example.test.application.usecase.Prescription;

import com.example.test.domain.model.Prescription;
import com.example.test.domain.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GetListPrescriptionUseCase {
    private final PrescriptionRepository prescriptionRepository;

    public GetListPrescriptionUseCase(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }
    public List<Prescription> execute(){
        return prescriptionRepository.findAllPrescriptions();
    }
}

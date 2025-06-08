package com.example.test.application.service;

import com.example.test.application.usecase.Prescription.GetListPrescriptionUseCase;
import com.example.test.application.usecase.Prescription.GetPrescriptionUseCase;
import com.example.test.application.usecase.Prescription.SavePrescriptionUseCase;
import com.example.test.application.usecase.Prescription.GetPrescriptionUseCase;
import com.example.test.domain.model.Prescription;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PrescriptionService {
    private final SavePrescriptionUseCase savePrescriptionUseCase;
    private final GetPrescriptionUseCase getPrescriptionUseCase;
    private final GetListPrescriptionUseCase getListPrescriptionUseCase;

    public PrescriptionService(SavePrescriptionUseCase savePrescriptionUseCase, GetPrescriptionUseCase getPrescriptionUseCase, GetListPrescriptionUseCase getListPrescriptionUseCase) {
        this.savePrescriptionUseCase = savePrescriptionUseCase;
        this.getPrescriptionUseCase = getPrescriptionUseCase;
        this.getListPrescriptionUseCase = getListPrescriptionUseCase;
    }
    public Prescription savePrescription(Prescription prescription){
        return savePrescriptionUseCase.execute(prescription);
    }

    public List<Prescription> getListPrescriptions(){
        return getListPrescriptionUseCase.execute();
    }

    public Optional<Prescription> getPrescriptionById(String id){
        return getPrescriptionUseCase.execute(id);
    }



}

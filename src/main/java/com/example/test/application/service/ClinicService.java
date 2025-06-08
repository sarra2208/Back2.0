package com.example.test.application.service;


import com.example.test.application.usecase.Clinic.GetClinicUseCase;
import com.example.test.application.usecase.Clinic.GetListClinicUseCase;
import com.example.test.application.usecase.Clinic.SaveClinicUseCase;

import com.example.test.domain.model.Clinic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClinicService {
    private final SaveClinicUseCase saveClinicUseCase;
    private final GetListClinicUseCase getListClinicUseCase;
    private final GetClinicUseCase getClinicUseCase;

    public ClinicService(SaveClinicUseCase saveClinicUseCase, GetListClinicUseCase getListClinicUseCase, GetClinicUseCase getClinicUseCase) {
        this.saveClinicUseCase = saveClinicUseCase;
        this.getListClinicUseCase = getListClinicUseCase;
        this.getClinicUseCase = getClinicUseCase;
    }


    public Clinic saveClinic(Clinic clinic) {
        return saveClinicUseCase.execute(clinic);
    }

    public List<Clinic> getListClinics() {
        return getListClinicUseCase.execute();
    }

    public Optional<Clinic> getClinicById(String id) {
        return getClinicUseCase.execute(id);
    }

}

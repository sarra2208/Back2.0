package com.example.test.application.usecase.Clinic;

import com.example.test.domain.model.Clinic;
import com.example.test.domain.repository.ClinicRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveClinicUseCase {
    private final ClinicRepository clinicRepository;

    public SaveClinicUseCase(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public Clinic execute(Clinic clinic){
        return clinicRepository.save(clinic);
    }
}
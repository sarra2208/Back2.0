package com.example.test.application.usecase.Clinic;

import com.example.test.domain.model.Clinic;
import com.example.test.domain.repository.ClinicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetListClinicUseCase {
    private final ClinicRepository clinicRepository;

    public GetListClinicUseCase(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public List<Clinic> execute(){
        return clinicRepository.findAllClinics();
    }
}
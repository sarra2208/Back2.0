package com.example.test.application.usecase.Clinic;


import com.example.test.domain.model.Clinic;
import com.example.test.domain.repository.ClinicRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GetClinicUseCase {
   private final ClinicRepository clinicRepository ;

    public GetClinicUseCase(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }
    public Optional<Clinic> execute(String id){

        return clinicRepository.findClinicById(id);
    }
}

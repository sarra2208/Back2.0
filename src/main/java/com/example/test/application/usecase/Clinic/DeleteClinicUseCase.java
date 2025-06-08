package com.example.test.application.usecase.Clinic;


import com.example.test.domain.repository.ClinicRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteClinicUseCase {

    private final ClinicRepository clinicRepository;





    public DeleteClinicUseCase(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public  void execute(String id) {
        clinicRepository.deleteById(id);
    }
}

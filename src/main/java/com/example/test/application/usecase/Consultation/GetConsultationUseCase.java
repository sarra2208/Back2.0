package com.example.test.application.usecase.Consultation;

import com.example.test.domain.model.Appointment;
import com.example.test.domain.model.Consultation;
import com.example.test.domain.repository.ConsultationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GetConsultationUseCase {

    private final ConsultationRepository consultationRepository;

    public GetConsultationUseCase(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }
    public Optional<Consultation> execute(String id){

        return consultationRepository.findConsultationById(id);
    }
}

package com.example.test.application.usecase.Consultation;


import com.example.test.domain.model.Consultation;
import com.example.test.domain.repository.ConsultationRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveConsultationUseCase {
   private final ConsultationRepository consultationRepository;

    public SaveConsultationUseCase(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }
    public Consultation execute(Consultation consultation){
        return consultationRepository.save(consultation);
    }
}

package com.example.test.application.usecase.Consultation;

import com.example.test.domain.model.Consultation;
import com.example.test.domain.repository.ConsultationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GetListConsultationUseCase {
    private final ConsultationRepository consultationRepository;

    public GetListConsultationUseCase(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }
    public List<Consultation> execute(){
        return consultationRepository.findAllConsultations();
    }
}


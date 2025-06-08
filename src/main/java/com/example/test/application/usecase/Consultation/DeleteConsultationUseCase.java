package com.example.test.application.usecase.Consultation;

import com.example.test.domain.repository.ConsultationRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteConsultationUseCase {
    private final ConsultationRepository consultationRepository;

    public DeleteConsultationUseCase(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }
    public  void execute(String id) {
        consultationRepository.deleteById(id);
    }
}

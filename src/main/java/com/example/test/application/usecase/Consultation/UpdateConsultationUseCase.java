package com.example.test.application.usecase.Consultation;

import com.example.test.domain.model.Consultation;
import com.example.test.domain.repository.ConsultationRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateConsultationUseCase {
    private final ConsultationRepository consultationRepository;

    public UpdateConsultationUseCase(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }
    public Consultation execute(String id, Consultation updatedConsultation) {
        return consultationRepository.findConsultationById(id)
                .map(existingConsultation -> {
                    // Mettre à jour uniquement si l'ID existe
                    existingConsultation.setType(updatedConsultation.getType());
                    existingConsultation.setStatus(updatedConsultation.getNote());
                    existingConsultation.setNote(updatedConsultation.getStatus());

                    return consultationRepository.save(existingConsultation);
                })
                .orElseThrow(() -> new RuntimeException("⚠️ Appointment not found with ID: " + id));
    }
}

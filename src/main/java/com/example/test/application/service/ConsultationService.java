package com.example.test.application.service;

import com.example.test.application.usecase.Consultation.GetConsultationUseCase;
import com.example.test.application.usecase.Consultation.GetListConsultationUseCase;
import com.example.test.application.usecase.Consultation.SaveConsultationUseCase;
import com.example.test.domain.model.Consultation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ConsultationService {
    private final SaveConsultationUseCase saveConsultationUseCase;
    private final GetListConsultationUseCase getListConsultationUseCase;
    private final GetConsultationUseCase getConsultationUseCase;

    public ConsultationService(SaveConsultationUseCase saveConsultationUseCase, GetListConsultationUseCase getListConsultationUseCase, GetConsultationUseCase getConsultationUseCase) {
        this.saveConsultationUseCase = saveConsultationUseCase;
        this.getListConsultationUseCase = getListConsultationUseCase;
        this.getConsultationUseCase = getConsultationUseCase;
    }


    public Consultation saveConsultation(Consultation Consultation) {
        return saveConsultationUseCase.execute(Consultation);
    }

    public List<Consultation> getListConsultations() {
        return getListConsultationUseCase.execute();
    }

    public Optional<Consultation> getConsultationById(String id) {
        return getConsultationUseCase.execute(id);
    }
}
